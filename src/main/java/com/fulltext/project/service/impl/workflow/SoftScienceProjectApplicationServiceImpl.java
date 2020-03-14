package com.fulltext.project.service.impl.workflow;

import com.fulltext.project.bo.WorkFlowNode;
import com.fulltext.project.service.impl.WorkFlowServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/14.
 *
 * @author anlu.
 */
@Service
public class SoftScienceProjectApplicationServiceImpl extends WorkFlowServiceImpl {
    @Override
    public void createWholeFlow(WorkFlowNode rootNode) {
//        1、申请单位提交表单，提交表单的PDF
//        2、申报单位审核，审核人角色：提报人上级
//        3、处员初审，审核人角色：评审专家委员会办公室处员
//        4、处长审核，审核人角色：评审专家委员会办公室处长
//        5、主管副主任审核，审核人角色：评审专家委员会办公室主管副主任
//        6、主任审核，审核人角色：评审专家委员会办公室主任
//        7、多对多专家评审，审核人角色：软科学课题申报专家，引入一个新的表单：评审表
//        8、报送研究成果，无需审核，上传结题材料电子版和纸质版扫描件
//        9、多对多专家评审，审核人角色：软科学课题申报专家，引入一个新的表单：结题评审表
        List<String> stepOneAttachList = new ArrayList<>();
        stepOneAttachList.add("申请表PDF扫描件");
        rootNode.setAttachmentNameList(stepOneAttachList);
        rootNode.setFlowNo("1-1");
        rootNode.setFormNo("form-1-1");

        WorkFlowNode stepTwoNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-2")
                .needApproval(true)
                .approvalRole("leader")
                .build();

        rootNode.setNextFlow(stepTwoNode);

        WorkFlowNode stepThreeNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-3")
                .needApproval(true)
                .approvalRole("soft-office-member")
                .build();

        stepTwoNode.setNextFlow(stepThreeNode);

        WorkFlowNode stepFourNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-4")
                .needApproval(true)
                .approvalRole("soft-office-master")
                .build();

        stepThreeNode.setNextFlow(stepFourNode);

        WorkFlowNode stepFiveNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-5")
                .needApproval(true)
                .approvalRole("soft-office-deputy-director")
                .build();

        stepFourNode.setNextFlow(stepFiveNode);

        WorkFlowNode stepSixNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-6")
                .needApproval(true)
                .approvalRole("soft-office-director")
                .build();

        stepFiveNode.setNextFlow(stepSixNode);

        WorkFlowNode stepSevenNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-7")
                .needApproval(true)
                .approvalRole("soft-office-expert")
                .formNo("form-1-7")
                .build();

        stepSixNode.setNextFlow(stepSevenNode);

        List<String> stepEightAttachList = new ArrayList<>();
        stepEightAttachList.add("结题材料电子版");
        stepEightAttachList.add("结题材料纸质版扫描件");

        WorkFlowNode stepEightNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-8")
                .needApproval(false)
                .attachmentNameList(stepEightAttachList)
                .build();

        stepSevenNode.setNextFlow(stepEightNode);

        WorkFlowNode stepNineNode = WorkFlowNode.builder()
                .flowName(rootNode.getFlowName())
                .flowNo("1-9")
                .needApproval(true)
                .approvalRole("soft-office-expert")
                .formNo("form-1-9")
                .build();

        stepEightNode.setNextFlow(stepNineNode);


    }
}
