package com.fulltext.project.bo;

import lombok.Builder;
import lombok.Data;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/11.
 *
 * @author anlu.
 */
@Data
@Builder
public class DocumentSearchBO {
    private String id;
    private Long documentId;
    private Long menuId;
    private String documentName;
    private String menuName;
    private String menuFullName;
    private String pageInfo;

    public String getId() {
        return documentId+"-"+menuId;
    }
}
