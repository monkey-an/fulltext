package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.bo.WorkFlowNode;
import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.entity.*;
import com.fulltext.project.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/30.
 *
 * @author anlu.
 */

@Service
@Slf4j
public class SoftScienceDoneApplicationService extends WorkFlowServiceImpl{
    public static final String approval_html_temp = "<table><tr>\n" +
            "            <td rowspan='2'>%1$s意见</td>\n" +
            "            <td colspan='4' style='text-align: left;border-bottom-color: #fff;'>\n" +
            "                <lable>同意</lable><input type='radio' name='drone' value='同意' checked>\n" +
            "                <lable>不同意</lable><input type='radio' name='drone' value='不同意'>\n" +
            "                 <input type='text' class='form-control' name='approvalMsg' value='' placeholder='请填写审批意见'>"+
            "                " +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td colspan='4' style='text-align: right;'>审核人：%2$s</td>\n" +
            "        </tr></table>";

    private static Map<String,String> formHtmlToFormNo = new HashMap<>();
    static {
        formHtmlToFormNo.put("form22","form-2-2");
    }


    @Override
    public void createWholeFlow(WorkFlowNode rootNode) {
//        1、报送研究成果，无需审核，上传结题材料电子版和纸质版扫描件
//        2、多对多专家评审，审核人角色：软科学课题申报专家，引入一个新的表单：结题评审表
        List<String> stepOneAttachList = new ArrayList<>();
        stepOneAttachList.add("结题材料电子版");
        stepOneAttachList.add("结题材料纸质版扫描件");
        rootNode.setAttachmentNameList(stepOneAttachList);
        rootNode.setFlowNo("2-1");


        WorkFlowNode stepTwoNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("2-2")
                .nodeName("专家复审")
                .needApproval(true)
                .approvalRole("soft-office-expert")
                .formNo("form-2-2")
                .build();

        rootNode.setNextFlow(stepTwoNode);
    }

    @Override
    public WorkFlowNode getRootNode() {
        WorkFlowNode rootNode = flowRootNodeMap.get(softScienceDoneApplication);
        createWholeFlow(rootNode);
        return rootNode;
    }

    @Override
    public String process(HttpServletRequest request) {
        String result = "";
        try {
            //先判断当前处于哪个节点
            String flowNo = request.getParameter("flow-no");
            String flowName = request.getParameter("flow-name");
            String form22Html = request.getParameter("form-2-2");

            WorkFlowNode currentNode = null;
            WorkFlowNode rootNode = flowRootNodeMap.get(flowName);

            int findCount = 0;
            while (rootNode != null) {
                if (rootNode.getFlowNo().equals(flowNo)) {
                    currentNode = rootNode;
                    break;
                } else {
                    rootNode = rootNode.getNextFlow();
                    findCount++;
                }
            }

            if (currentNode == null) {
                log.info("找不到当前节点的位置" + flowNo);
                return "ERROR";
            }

            User user = (User) request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
//            User user = new User();

            Task task = null;
            if (findCount == 0) {
                //说明是此流程第一个节点，初始化流程
                TaskIdSeq taskIdSeq = new TaskIdSeq();
                taskIdSeqService.insert(taskIdSeq);

                task = Task.builder()
                        .taskId(taskIdSeq.getId())
                        .taskName(currentNode.getFlowName())
                        .commitUserId(user.getId())
                        .commitUserName(user.getRealName())
                        .currentApprovalUserCount(1)
                        .currentNodeNo(currentNode.getFlowNo())
                        .currentApprovalStatus("DOING")
                        .createTime(new Date())
                        .updateTime(new Date())
                        .build();

                taskService.insert(task);
            } else {
                //去数据库把本流程数据取出来
                Long taskId = Long.parseLong(request.getParameter("task-id"));
                task = taskService.selectTaskByTaskId(taskId);
            }

            //写taskDetail
            TaskDetail taskDetail = TaskDetail.builder()
                    .taskId(task.getTaskId())
                    .operNodeNo(currentNode.getFlowNo())
                    .operUserId(user.getId())
                    .operUserName(user.getRealName())
                    .operType(findCount==0?"CREATE":"APPROVAL")
                    .operResult("SUCCESS")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();

            //当前节点需要处理的附件，存起来
            if (!CollectionUtils.isEmpty(currentNode.getAttachmentNameList())) {
                String filePath = ConstantValue.TASK_ATTACHMENT_FILE_PATH;
                for (String attachName : currentNode.getAttachmentNameList()) {
                    MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                    MultipartFile file = multipartRequest.getFile("attach-" + attachName);
                    if (file != null && file.getSize() > 0) {
                        String fileName = file.getOriginalFilename();
                        try {
                            FileUploadUtil.saveFile(file.getBytes(), filePath, fileName);
                            TaskAttachment taskAttachment = TaskAttachment.builder()
                                    .taskId(task.getTaskId())
                                    .attachmentName(fileName)
                                    .path(filePath + fileName)
                                    .createTime(new Date())
                                    .updateTime(new Date())
                                    .build();

                            taskAttachmentService.insert(taskAttachment);
                        } catch (Exception e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            }

            //当前节点的表单，存起来
            createOrUpdateHtml(form22Html, task,user,currentNode);

            //判断当前节点是否需要审批
            //如果当前节点需要审批，通过审批表单最后一行取，判断是否有同意两个字
            //如果当前节点通过了，且有下一个节点，推进到下一节点，否则整个流程结束
            WorkFlowNode nextNode = null;
            if (currentNode.isNeedApproval()) {
                Document tableDoc = Jsoup.parse(form22Html);
                Elements lastTrTds = tableDoc.select("tr").select("td");
                String approvalResult = lastTrTds.get(lastTrTds.size()-2).html();
                if (!approvalResult.contains("不同意")) {
                    nextNode = currentNode.getNextFlow();
                }else{
                    nextNode = null;
                    taskDetail.setOperResult("REJECT");
                }
            }else{
                nextNode = currentNode.getNextFlow();
            }

            //获取下一节点，判断下一节点是否需要审核，如果需要审核，找审核人，如果找不到审核人，说明流程应该结束（自己就是大老板那种），如果不需要审核，那么审核人是推回自己，说明需要提交一些材料上来
            if (nextNode == null) {
                //当前节点需要处理的人数，和实际已处理的人数相同，才继续往下一节点走
                //获取当前已处理人数
                List<TaskDetail> taskDetailList = taskDetailService.selectTaskDetailByTaskIdAndOperNodeNo(task.getTaskId(),currentNode.getFlowNo());

                if(taskDetailList.stream().map(TaskDetail::getOperUserId).distinct().count()==(task.getCurrentApprovalUserCount()-1)) {
                    //结束，把当前处理用户再推回提交用户
                    if (!"REJECT".equals(taskDetail.getOperResult())) {
                        //正常结束
                        task.setCurrentApprovalStatus("DONE");
                    } else {
                        //被拒绝
                        task.setCurrentApprovalStatus("REJECT");
                    }
                    User commiter = userService.selectUserById(task.getCommitUserId());
                    task.setCurrentApprovalUserId(commiter.getId() + "");
                    task.setCurrentApprovalUserName(commiter.getRealName());
                    task.setCurrentApprovalUserCount(1);
                }
            } else {
                //当前节点需要处理的人数，和实际已处理的人数相同，才继续往下一节点走
                //获取当前已处理人数
                List<TaskDetail> taskDetailList = taskDetailService.selectTaskDetailByTaskIdAndOperNodeNo(task.getTaskId(),currentNode.getFlowNo());

                if(taskDetailList.stream().map(TaskDetail::getOperUserId).distinct().count()==(task.getCurrentApprovalUserCount()-1)) {
                    //所有人都审核完了
                    //继续下一节点
                    //更新数据库，写下一节点的处理状态及信息，返回成功。
                    task.setCurrentNodeNo(nextNode.getFlowNo());
                    task.setCurrentNodeName(nextNode.getNodeName());

                    if (nextNode.isNeedApproval()) {
                        //推给审核者
                        List<User> targetUser = new ArrayList<>();
                        switch (nextNode.getApprovalRole()) {
                            case "leader":
                                Department department = departmentService.selectDepartmentById(user.getDepartmentId());
                                if (user.getId().equals(department.getLeaderUserId())) {
                                    Department upperDepartment = departmentService.selectDepartmentById(department.getParentDepartmentId());
                                    if (upperDepartment != null) {
                                        User tempUser = userService.selectUserById(upperDepartment.getLeaderUserId());
                                        targetUser.add(tempUser);
                                    }
                                } else {
                                    User tempUser = userService.selectUserById(department.getLeaderUserId());
                                    targetUser.add(tempUser);
                                }
                                break;
                            case "soft-office-member":
                            case "soft-office-master":
                            case "soft-office-deputy-director":
                            case "soft-office-director":
                            case "soft-office-expert":
                                List<UserRole> userRoleList = userRoleService.selectListByRoleName(nextNode.getApprovalRole());
                                if (!CollectionUtils.isEmpty(userRoleList)) {
                                    List<Long> userIdList = userRoleList.stream().map(UserRole::getUserId).collect(Collectors.toList());
                                    targetUser = userService.selectUserListByIdList(userIdList);
                                }
                                break;
                        }
                        //需要审核但找不到审核人，说明流程应该结束（自己就是大老板那种）
                        if (CollectionUtils.isEmpty(targetUser)) {
                            task.setCurrentApprovalUserId(task.getCommitUserId()+"");
                            task.setCurrentApprovalUserName(task.getCommitUserName());
                            task.setCurrentApprovalUserCount(1);
                            task.setCurrentApprovalStatus("DONE");
                        } else {
                            StringBuilder userIdString = new StringBuilder();
                            StringBuilder userNameString = new StringBuilder();
                            targetUser.forEach(u->{
                                userIdString.append(","+u.getId());
                                userNameString.append(","+u.getRealName());
                            });
                            task.setCurrentApprovalUserId(userIdString+",");
                            task.setCurrentApprovalUserCount(targetUser.size());
                            task.setCurrentApprovalUserName(userNameString+",");
                        }

                    } else {
                        //推给提交者
                        User commiter = userService.selectUserById(task.getCommitUserId());
                        task.setCurrentApprovalUserId(commiter.getId() + "");
                        task.setCurrentApprovalUserCount(1);
                        task.setCurrentApprovalUserName(commiter.getRealName());
                    }
                }
            }

            taskService.update(task);
            taskDetailService.insert(taskDetail);

            result = "SUCCESS";
        }catch (Exception e){
            //流程中有其他异常，返回提交失败，重新走这一节点
            result = "ERROR";
        }

        return result;
    }

    private void createOrUpdateHtml(String form22Html,Task task,User user,WorkFlowNode currentNode){
        if (StringUtils.isNotEmpty(form22Html)) {
            //直接写
            String formNo = formHtmlToFormNo.get("form22");
            TaskFormHtml taskFormHtml = TaskFormHtml.builder()
                    .taskId(task.getTaskId())
                    .commitUserId(user.getId())
                    .nodeName(currentNode.getNodeName())
                    .commitUserName(user.getRealName())
                    .formNo(formNo)
                    .formContent(form22Html)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            taskFormHtmlService.insert(taskFormHtml);
        }
    }
}
