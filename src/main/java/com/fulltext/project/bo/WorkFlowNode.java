package com.fulltext.project.bo;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/14.
 *
 * @author anlu.
 */

@Data
@Builder
public class WorkFlowNode {
    //流程名称
    private String flowName;
    //流程编号
    private String flowNo;
    //需要添加进流程的表单编号
    private String formNo;
    //此节点是否需要审批
    private boolean needApproval = true;
    //需要审批的角色名
    private String approvalRole;
    //需要的附件列表
    private List<String> attachmentNameList;
    //已有附件下载连接
    private Map<String,String> attachmentDownLoadUrl;
    //当前节点审批状态：驳回、通过、审批中 REJECT DONE DOING
    private String currentApprovalStatus;
    //当前节点处理人名
    private String currentApprovalUserName;
    //当前节点处理人ID
    private String currentApprovalUserId;
    //当前已有表单map,key->formName+formNo,value->formHtml
    private Map<String,String> formMap = new HashMap<>();

    //下一节点
    private WorkFlowNode nextFlow;
}
