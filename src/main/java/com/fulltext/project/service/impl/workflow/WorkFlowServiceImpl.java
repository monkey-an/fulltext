package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.bo.WorkFlowNode;
import com.fulltext.project.service.WorkFlowService;
import lombok.extern.slf4j.Slf4j;
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
    protected static final String softScienceProjectApplication = "软科学课题申报";

    protected static final Map<String, WorkFlowNode> flowRootNodeMap = new HashMap<>();

    public abstract void createWholeFlow(WorkFlowNode rootNode);

    static {
        WorkFlowNode softScienceRootNode = WorkFlowNode.builder()
                .flowName(softScienceProjectApplication)
                .needApproval(false)
                .build();

        flowRootNodeMap.put(softScienceProjectApplication,softScienceRootNode);
    }

    public abstract WorkFlowNode getRootNode();


}
