package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.bo.WorkFlowNode;
import com.fulltext.project.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/14.
 *
 * @author anlu.
 */

@Service
@Slf4j
public abstract class WorkFlowServiceImpl implements WorkFlowService {
    @Autowired
    protected TaskIdSeqService taskIdSeqService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected TaskFormHtmlService taskFormHtmlService;

    @Autowired
    protected TaskDetailService taskDetailService;

    @Autowired
    protected TaskAttachmentService taskAttachmentService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected UserRoleService userRoleService;

    @Autowired
    protected DepartmentService departmentService;


    protected static final String softScienceProjectApplication = "软科学课题申报";
    protected static final String reportSignApplication = "签报";
    protected static final String softScienceDoneApplication = "软科学课题结题";

    protected static final Map<String, WorkFlowNode> flowRootNodeMap = new HashMap<>();

    public abstract void createWholeFlow(WorkFlowNode rootNode);

    static {
        WorkFlowNode softScienceRootNode = WorkFlowNode.builder()
                .flowName(softScienceProjectApplication)
                .nodeName("提交申请")
                .needApproval(false)
                .build();

        flowRootNodeMap.put(softScienceProjectApplication,softScienceRootNode);

        WorkFlowNode reportSignRootNode = WorkFlowNode.builder()
                .flowName(reportSignApplication)
                .nodeName("提交申请")
                .needApproval(false)
                .build();

        flowRootNodeMap.put(reportSignApplication,reportSignRootNode);

        WorkFlowNode softScienceDoneRootNode = WorkFlowNode.builder()
                .flowName(softScienceDoneApplication)
                .nodeName("报送研究成果")
                .needApproval(false)
                .build();

        flowRootNodeMap.put(softScienceDoneApplication,softScienceDoneRootNode);
    }

    public abstract WorkFlowNode getRootNode();


}
