package com.fulltext.project.bo;

import com.fulltext.project.entity.DocumentMenu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
public class DocumentMenuNode {
    private DocumentMenu node;
    private List<DocumentMenuNode> subNodeList;
}
