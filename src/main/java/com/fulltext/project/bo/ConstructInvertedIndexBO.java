package com.fulltext.project.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/12.
 *
 * @author anlu.
 */

@Data
@Builder
public class ConstructInvertedIndexBO {
    private String businessId;
    private String title;//书名 ｜｜ 文章名 docuemnt_name || menu_name
    private String summary;
    private String body;
    private List<String> keyWords;
    private String author;
    private String members;
}
