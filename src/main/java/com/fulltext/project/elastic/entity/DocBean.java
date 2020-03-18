package com.fulltext.project.elastic.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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
@NoArgsConstructor
@Accessors(chain = true)
@Document(indexName = "book", type = "_doc", shards = 1, replicas = 0)
@Builder
public class DocBean {
    @Id
    private String businessId;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;//书名 ｜｜ 文章名 docuemnt_name || menu_name

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String summary;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String body;

    @Field(type = FieldType.Keyword)
    private List<String> keyWords;

    @Field(type = FieldType.Keyword)
    private String author;

    @Field(type = FieldType.Keyword)
    private List<String> members;


    public DocBean(String businessId,String title,String summary,String body,List<String> keyWords, String author, List<String> members){
        this.businessId = businessId;
        this.title = title;
        this.summary = summary;
        this.body = body;
        this.keyWords = keyWords;
        this.author = author;
        this.members = members;
    }
}