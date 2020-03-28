package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.bo.WorkFlowNode;
import com.fulltext.project.dao.TaskMapper;
import com.fulltext.project.entity.*;
import com.fulltext.project.service.*;
import com.fulltext.project.service.impl.workflow.SoftScienceProjectApplicationServiceImpl;
import com.fulltext.project.service.impl.workflow.WorkFlowServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2020/03/14.
 */
@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskMapper taskMapper;

    @Autowired
    private TaskDetailService taskDetailService;

    @Autowired
    private TaskAttachmentService taskAttachmentService;

    @Autowired
    private TaskFormHtmlService taskFormHtmlService;

    @Autowired
    private SoftScienceProjectApplicationServiceImpl softScienceProjectApplicationService;

    @Autowired
    private ReportSignApplicationServiceImpl reportSignApplicationService;

    @Autowired
    private TaskFormService taskFormService;


    @Override
    public Task selectTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> selectTaskListByIdList(List<Long> idList) {
        return null;
    }

    private List<Task> selectTaskListByTaskIdList(List<Long> taskIdList) {
        return taskMapper.selectTaskListByTaskIdList(taskIdList);
    }

    @Override
    public int insert(Task entity) {
        return taskMapper.insert(entity);
    }

    @Override
    public int update(Task entity) {
        return taskMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Task selectTaskByTaskId(Long taskId) {
        return taskMapper.selectTaskByTaskId(taskId);
    }

    @Override
    public List<Task> selectByCommitUserId(Long userId) {
        List<Task> temp = taskMapper.selectByCommitUserId(userId);
        return temp;
    }

    @Override
    public List<Task> selectByCurrentUserId(Long userId) {
        List<Task> temp = taskMapper.selectByCurrentUserId(userId+"");
        temp = temp.stream()
                .filter(task->task.getCurrentApprovalStatus().equals("DOING"))
                .collect(Collectors.toList());
        return temp;
    }

    @Override
    public String initTaskDetail(User user, Long taskId, Model model) {
        //读取当前task详情，DOING状态
        Task task = selectTaskByTaskId(taskId);
        if(!"DOING".equals(task.getCurrentApprovalStatus())){
            return "ERROR";
        }

        //判断当前登录用户是不是审核人
        //如果不是，返回error，无权查看
        List<String> currentuserList = Arrays.asList(task.getCurrentApprovalUserId().split(","));
        if(!currentuserList.contains(user.getId()+"")){
            return "ERROR";
        }

        //task装进model
        model.addAttribute("task",task);

        WorkFlowNode rootNode = getRootNode(task.getTaskName());
        WorkFlowNode currentNode = null;
        while (rootNode != null) {
            if (rootNode.getFlowNo().equals(task.getCurrentNodeNo())) {
                currentNode = rootNode;
                break;
            } else {
                rootNode = rootNode.getNextFlow();
            }
        }

        if (currentNode == null) {
            log.info("找不到当前节点的位置,task:" + task.toString());
            return "ERROR";
        }

        model.addAttribute("currentNode",currentNode);
        String currentFormNo = currentNode.getFlowNo();

        //取出taskDetail，装进model
        List<TaskDetail> taskDetailList = taskDetailService.selectTaskDetailByTaskId(task.getTaskId());
        List<TaskDetail> tempList = taskDetailList.stream().filter(taskDetail -> taskDetail.getOperNodeNo().equals(currentFormNo)&& taskDetail.getOperUserId().equals(user.getId())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(tempList)){
            return "ERROR";
        }

        Map<String,TaskDetail> taskDetailFormNoMap = new HashMap<>();
        taskDetailList.forEach(taskDetail -> taskDetailFormNoMap.put(taskDetail.getOperNodeNo(),taskDetail));
        List<TaskDetail> washupTaskDetailList = new ArrayList<>();
        washupTaskDetailList.addAll(taskDetailFormNoMap.values());
        model.addAttribute("taskDetailList",washupTaskDetailList);

        //取出附件下载链接,装进model
        List<TaskAttachment> taskAttachmentList = taskAttachmentService.selectTaskAttachmentListByTaskId(task.getTaskId());
        if(taskAttachmentList!=null&&taskAttachmentList.size()>0) {
            model.addAttribute("taskAttachmentList", taskAttachmentList);
        }

        //取出表单
        List<TaskFormHtml> taskFormHtmlList = taskFormHtmlService.selectTaskFormHtmlByTaskId(task.getTaskId());
        taskFormHtmlList = taskFormHtmlList.stream().filter(taskFormHtml -> task.getCommitUserId().equals(taskFormHtml.getCommitUserId())).collect(Collectors.toList());
        //在第一个表单上生成审批意见
        if(currentNode.isNeedApproval()) {
            if (taskFormHtmlList != null && taskFormHtmlList.size() > 0) {
                TaskFormHtml taskFormHtml = taskFormHtmlList.get(0);
                String html = taskFormHtml.getFormContent();
                Document doc = Jsoup.parse(html);
                String addHtml = String.format(getApprovalHtml(task.getTaskName()),currentNode.getNodeName(),user.getRealName());
                Element table = doc.select("table tbody").first();
                table.insertChildren(table.childNodeSize()-1,Jsoup.parse(addHtml).select("tr"));
                taskFormHtml.setFormContent(doc.select("table").first().outerHtml());
            }
        }

        if(currentuserList.contains(task.getCommitUserId()+"")){
            taskFormHtmlList = taskFormHtmlList.subList(0,1);
        }
        //装进model
        if(taskAttachmentList!=null && taskAttachmentList.size()>0) {
            model.addAttribute("taskFormHtmlList", taskFormHtmlList);
        }

        //判断有无新增表单
        if(StringUtils.isNotEmpty(currentNode.getFormNo())){
            TaskForm taskForm = taskFormService.selectTaskFormByFormNo(currentNode.getFormNo());
            if(taskForm!=null){
                model.addAttribute("taskForm",taskForm);
            }
        }

        if ("DOING".equals(task.getCurrentApprovalStatus())) {
            //待审核人
            List<TaskDetail> nowNodetaskDetailList = taskDetailService.selectTaskDetailByTaskIdAndOperNodeNo(task.getTaskId(), currentNode.getFlowNo());

            if (nowNodetaskDetailList.stream().map(TaskDetail::getOperUserId).distinct().count() != (task.getCurrentApprovalUserCount())) {
                List<String> tempApprovalUserList = new ArrayList<>();
                tempApprovalUserList.addAll(Arrays.asList(task.getCurrentApprovalUserName().split(",")));

                Iterator<String> iterator = tempApprovalUserList.iterator();
                while (iterator.hasNext()) {
                    String userName = iterator.next();
                    if (nowNodetaskDetailList.stream().anyMatch(taskDetail -> userName.equals(taskDetail.getOperUserName()))) {
                        iterator.remove();
                    }
                }

                if (tempApprovalUserList.size() > 0) {
                    model.addAttribute("waitingApprovalUserList", tempApprovalUserList);
                }
            }
        }

        //返回success
        return "SUCCESS";
    }

    private String getApprovalHtml(String taskName) {
        String result = "";
        switch (taskName){
            case "软科学课题申报":
                result = SoftScienceProjectApplicationServiceImpl.approval_html_temp;
                break;
            case "签报":
                result = ReportSignApplicationServiceImpl.approval_html_temp;
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public String initMyTaskDetail(User user, Long taskId, Model model) {
        //读取当前task详情，DOING状态
        Task task = selectTaskByTaskId(taskId);

        //判断当前登录用户是不是提交人
        //如果不是，返回error，无权查看
        if(!user.getId().equals(task.getCommitUserId())){
            return "ERROR";
        }

        //task装进model
        model.addAttribute("task",task);

        WorkFlowNode rootNode = getRootNode(task.getTaskName());
        WorkFlowNode currentNode = null;
        while (rootNode != null) {
            if (rootNode.getFlowNo().equals(task.getCurrentNodeNo())) {
                currentNode = rootNode;
                break;
            } else {
                rootNode = rootNode.getNextFlow();
            }
        }

        if (currentNode == null) {
            log.info("找不到当前节点的位置,task:" + task.toString());
            return "ERROR";
        }

        model.addAttribute("currentNode",currentNode);

        //取出taskDetail，装进model
        List<TaskDetail> taskDetailList = taskDetailService.selectTaskDetailByTaskId(task.getTaskId());
        model.addAttribute("taskDetailList",taskDetailList);

        //取出附件下载链接,装进model
        List<TaskAttachment> taskAttachmentList = taskAttachmentService.selectTaskAttachmentListByTaskId(task.getTaskId());
        if(taskAttachmentList!=null&&taskAttachmentList.size()>0) {
            model.addAttribute("taskAttachmentList", taskAttachmentList);
        }

        //取出表单
        List<TaskFormHtml> taskFormHtmlList = taskFormHtmlService.selectTaskFormHtmlByTaskId(task.getTaskId());
        if (taskAttachmentList != null && taskAttachmentList.size() > 0) {
            taskFormHtmlList = taskFormHtmlList.subList(0, 1);
            //装进model
            model.addAttribute("taskFormHtmlList", taskFormHtmlList);
        }

        if ("DOING".equals(task.getCurrentApprovalStatus())) {
            //待审核人
            List<TaskDetail> nowNodetaskDetailList = taskDetailService.selectTaskDetailByTaskIdAndOperNodeNo(task.getTaskId(), currentNode.getFlowNo());

            if (nowNodetaskDetailList.stream().map(TaskDetail::getOperUserId).distinct().count() != (task.getCurrentApprovalUserCount())) {
                List<String> tempApprovalUserList = new ArrayList<>();
                tempApprovalUserList.addAll(Arrays.asList(task.getCurrentApprovalUserName().split(",")));

                Iterator<String> iterator = tempApprovalUserList.iterator();
                while (iterator.hasNext()) {
                    String userName = iterator.next();
                    if (nowNodetaskDetailList.stream().anyMatch(taskDetail -> userName.equals(taskDetail.getOperUserName()))) {
                        iterator.remove();
                    }
                }

                if (tempApprovalUserList.size() > 0) {
                    model.addAttribute("waitingApprovalUserList", tempApprovalUserList);
                }
            }
        }

        //返回success
        return "SUCCESS";
    }

    @Override
    public List<Task> selectByApprovalUserid(Long id) {
        List<Long> taskIdList = taskDetailService.selectTaskIdByOperUserId(id);
        if(taskIdList==null || taskIdList.size()==0){
            return null;
        }
        List<Task> taskList = selectTaskListByTaskIdList(taskIdList);
        return taskList;
    }

    @Override
    public String initMyApprovalTaskDetail(User user, Long taskId, Model model) {
        //读取当前task详情，DOING状态
        Task task = selectTaskByTaskId(taskId);

        List<TaskDetail> taskDetailList = taskDetailService.selectTaskDetailByTaskId(taskId);

        //判断当前登录用户是不是操作人
        //如果不是，返回error，无权查看
        if(taskDetailList.stream().noneMatch(taskDetail -> taskDetail.getOperUserId().equals(user.getId()))){
            return "ERROR";
        }

        //task装进model
        model.addAttribute("task",task);

        WorkFlowNode rootNode = getRootNode(task.getTaskName());
        WorkFlowNode currentNode = null;
        while (rootNode != null) {
            if (rootNode.getFlowNo().equals(task.getCurrentNodeNo())) {
                currentNode = rootNode;
                break;
            } else {
                rootNode = rootNode.getNextFlow();
            }
        }

        if (currentNode == null) {
            log.info("找不到当前节点的位置,task:" + task.toString());
            return "ERROR";
        }

        model.addAttribute("currentNode",currentNode);

        //取出taskDetail，装进model
        model.addAttribute("taskDetailList",taskDetailList);

        //取出附件下载链接,装进model
        List<TaskAttachment> taskAttachmentList = taskAttachmentService.selectTaskAttachmentListByTaskId(task.getTaskId());
        if(taskAttachmentList!=null&&taskAttachmentList.size()>0) {
            model.addAttribute("taskAttachmentList", taskAttachmentList);
        }

        //取出表单
        List<TaskFormHtml> taskFormHtmlList = taskFormHtmlService.selectTaskFormHtmlByTaskId(task.getTaskId());
        if (taskAttachmentList != null && taskAttachmentList.size() > 0) {
            taskFormHtmlList = taskFormHtmlList.subList(0, 1);
            //装进model
            model.addAttribute("taskFormHtmlList", taskFormHtmlList);
        }

        if ("DOING".equals(task.getCurrentApprovalStatus())) {
            //待审核人
            List<TaskDetail> nowNodetaskDetailList = taskDetailService.selectTaskDetailByTaskIdAndOperNodeNo(task.getTaskId(), currentNode.getFlowNo());

            if (nowNodetaskDetailList.stream().map(TaskDetail::getOperUserId).distinct().count() != (task.getCurrentApprovalUserCount())) {
                List<String> tempApprovalUserList = new ArrayList<>();
                tempApprovalUserList.addAll(Arrays.asList(task.getCurrentApprovalUserName().split(",")));

                Iterator<String> iterator = tempApprovalUserList.iterator();
                while (iterator.hasNext()) {
                    String userName = iterator.next();
                    if (nowNodetaskDetailList.stream().anyMatch(taskDetail -> userName.equals(taskDetail.getOperUserName()))) {
                        iterator.remove();
                    }
                }

                if (tempApprovalUserList.size() > 0) {
                    model.addAttribute("waitingApprovalUserList", tempApprovalUserList);
                }
            }
        }

        //返回success
        return "SUCCESS";
    }

    @Override
    public List<Long> selectAllTaskId() {
        return taskMapper.selectAllTaskId();
    }

    private WorkFlowNode getRootNode(String taskName) {
        WorkFlowServiceImpl workFlowService = null;
        switch (taskName){
            case "软科学课题申报":
                workFlowService = softScienceProjectApplicationService;
                break;
            case "签报":
                workFlowService = reportSignApplicationService;
                break;
            default:
                break;
        }

        if(workFlowService!=null){
            return workFlowService.getRootNode();
        }

        return null;
    }
}
