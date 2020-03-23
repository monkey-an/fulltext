package com.fulltext.project.bo;

import com.fulltext.project.entity.DocumentInfo;
import com.fulltext.project.entity.DocumentMenu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/7.
 *
 * @author anlu.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentSerialDetailBO {
    private DocumentInfo documentInfo;
    private List<DocumentMenuNode> documentMenuList;
    private String documentImagePath;
    private Map<String,DocumentMenu> menuNameMap;

    public String createMenuHtml() {
        StringBuilder sb = new StringBuilder();
        documentMenuList.forEach(menu -> createHtml(sb, menu, 0,0L));
        return sb.toString();
    }

    private void createHtml(StringBuilder sb, DocumentMenuNode menuNode, int level,Long parent) {
        if (menuNode != null) {
            String tagsClass = "tags";
            String minusClass = "plus";

            boolean hasSub = menuNode.getSubNodeList() != null && menuNode.getSubNodeList().size() > 0;

            sb.append("<p selfMenu='"+menuNode.getNode().getId()+"' parentMenu='"+parent+"'><span class='glyphicon glyphicon-" + (hasSub ? minusClass : tagsClass) + " col-md-offset-" + (level * 1) + "'>  "
                        + (hasSub ?
                                menuNode.getNode().getMenuName() :
                                "<a href='downLoadPage?menuId="+menuNode.getNode().getId()+"'>"+menuNode.getNode().getMenuName()+"</a>") +
                        "</span>" +"<span style='float:right;'>"+menuNode.getNode().getMenuPage()+"</span>"+
                    "</p>");
            if (hasSub) {
                ++level;
                for (DocumentMenuNode documentMenuNode : menuNode.getSubNodeList()) {
                    createHtml(sb, documentMenuNode, level, menuNode.getNode().getId());
                }
                --level;
            }
        }
    }

}
