package com.fulltext.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping("")
    public String workflowIndex() {
        return "workflow/index";
    }

    @RequestMapping("/getMainPage")
    public String getMainPage(HttpServletRequest request) {
        String funName = request.getParameter("funName");
        String resultView = "";
        if (StringUtils.isNotEmpty(funName)) {
            switch (funName) {
                case "work-mywork-input":
                    resultView = "workflow/mywork";
                    break;
                case "work-subject-apply-input":
                    resultView = "workflow/subject_apply";
                    break;
                case "work-expert-review-manage":
                    resultView = "workflow/expert_review";
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
}
