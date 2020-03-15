package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.bo.WorkFlowNode;
import com.fulltext.project.dao.TaskMapper;
import com.fulltext.project.entity.*;
import com.fulltext.project.service.*;
import com.fulltext.project.service.impl.workflow.SoftScienceProjectApplicationServiceImpl;
import com.fulltext.project.service.impl.workflow.WorkFlowServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2020/03/14.
 */
@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {
    private static final String approval_html_temp = "<table><tr>\n" +
            "            <td rowspan=\"2\">%1$s意见</td>\n" +
            "            <td colspan=\"4\" style=\"border-bottom-color: #fff;\">\n" +
            "                <button type=\"button\" class=\"btn btn-success\" id=\"agreey\">同意</button>\n" +
            "                <button type=\"button\" class=\"btn btn-success\" id=\"unagreey\">不同意</button>\n" +
            "            </td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "            <td colspan=\"4\" style=\"text-align: right;\">审核人：%2$s</td>\n" +
            "        </tr></table>";

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

    @Override
    public Task selectTaskById(Long id) {
        return null;
    }

    @Override
    public List<Task> selectTaskListByIdList(List<Long> idList) {
        return null;
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
        List<Task> temp = taskMapper.selectByCurrentUserId(userId);
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
        if(!user.getId().equals(Long.parseLong(task.getCurrentApprovalUserId()))){
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
        //在第一个表单上生成审批意见
        if(currentNode.isNeedApproval()) {
            if (taskFormHtmlList != null && taskFormHtmlList.size() > 0) {
                TaskFormHtml taskFormHtml = taskFormHtmlList.get(0);
                String html = taskFormHtml.getFormContent();
                Document doc = Jsoup.parse(html);
                String addHtml = String.format(approval_html_temp,currentNode.getNodeName(),user.getRealName());
                Element table = doc.select("table tbody").first();
                table.insertChildren(table.childNodeSize(),Jsoup.parse(addHtml).select("tr"));
                taskFormHtml.setFormContent(doc.select("table").first().outerHtml());
            }
        }

        if(task.getCommitUserId().equals(Long.parseLong(task.getCurrentApprovalUserId()))){
            taskFormHtmlList = taskFormHtmlList.subList(0,1);
        }
        //装进model
        if(taskAttachmentList!=null && taskAttachmentList.size()>0) {
            model.addAttribute("taskFormHtmlList", taskFormHtmlList);
        }

        //返回success
        return "SUCCESS";
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

        //返回success
        return "SUCCESS";
    }


    private WorkFlowNode getRootNode(String taskName) {
        WorkFlowServiceImpl workFlowService = null;
        switch (taskName){
            case "软科学课题申报":
                workFlowService = softScienceProjectApplicationService;
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
