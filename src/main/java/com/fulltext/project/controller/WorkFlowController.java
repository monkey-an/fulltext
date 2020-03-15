package com.fulltext.project.controller;

import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.entity.Task;
import com.fulltext.project.entity.User;
import com.fulltext.project.service.TaskService;
import com.fulltext.project.service.WorkFlowService;
import com.fulltext.project.service.impl.workflow.SoftScienceProjectApplicationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/12.
 *
 * @author anlu.
 */

@Controller
@RequestMapping("/workflow")
@Slf4j
public class WorkFlowController {
    @Autowired
    private SoftScienceProjectApplicationServiceImpl softScienceProjectApplicationService;

    @Autowired
    private TaskService taskService;

    @RequestMapping("")
    public String workflowIndex() {
        return "workflow/index";
    }

    @RequestMapping("/getMainPage")
    public String getMainPage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        String funName = request.getParameter("funName");
        String resultView = "";
        if (StringUtils.isNotEmpty(funName)) {
            switch (funName) {
                case "work-mywork-input":
                    List<Task> myTask = taskService.selectByCommitUserId(user.getId());
                    List<Task> toMeTask = taskService.selectByCurrentUserId(user.getId());
                    if(myTask!=null && myTask.size()>0) {
                        model.addAttribute("myTask", myTask);
                    }
                    if(toMeTask!=null && toMeTask.size()>0) {
                        model.addAttribute("toMeTask", toMeTask);
                    }
                    resultView = "workflow/mywork";
                    break;
                case "work-subject-apply-input":
                    model.addAttribute("rootNode",softScienceProjectApplicationService.getRootNode());
                    resultView = "workflow/subject_apply";
                    break;
                case "work-report-sign-manage":
                    resultView = "workflow/report_sign";
                    break;
                case "work-office-paper-manage":
                    resultView = "workflow/office_paper";
                    break;
                case "work-dispatch-paper-manage":
                    resultView = "workflow/dispatch_paper";
                    break;
                case "work-report-create-manage":
                    resultView = "workflow/report_create";
                    break;
                case "work-notify-create-manage":
                    resultView = "workflow/notify_create";
                    break;
                default:
                    break;
            }
        }
        return resultView;
    }

    @RequestMapping("/commitFlow")
    @ResponseBody
    public String commitFlow(HttpServletRequest request) {
        String flowName = request.getParameter("flow-name");
        WorkFlowService workFlowService = null;
        switch (flowName){
            case "软科学课题申报":
                workFlowService = softScienceProjectApplicationService;
                break;
            default:
                break;
        }

        String result = "";
        if(workFlowService != null){
            result = workFlowService.process(request);
        }

        return result;
    }

    @RequestMapping("/flowDetail")
    public String flowDetail(HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        String initResult = taskService.initTaskDetail(user,taskId,model);
        if("SUCCESS".equals(initResult)){
            return "workflow/flow_detail";
        }else{
            return "workflow/flow_error";
        }
    }

    @RequestMapping("/myFlowDetail")
    public String myFlowDetail(HttpServletRequest request,Model model) {
        User user = (User) request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        Long taskId = Long.parseLong(request.getParameter("taskId"));
        String initResult = taskService.initMyTaskDetail(user,taskId,model);
        if("SUCCESS".equals(initResult)){
            return "workflow/my_flow_detail";
        }else{
            return "workflow/flow_error";
        }
    }
}
