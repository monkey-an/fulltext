package com.fulltext.project.elastic.dao;

import com.fulltext.project.elastic.entity.DocBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/12.
 *
 * @author anlu.
 */
public interface ElasticsearchDao extends ElasticsearchRepository<DocBean, String> {
    //默认的注释
//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"title\" : \"?\"}}}}")
    List<DocBean> findByTitle(String title, Pageable pageable);

//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"summary\" : \"?\"}}}}")
    List<DocBean> findBySummary(String summary, Pageable pageable);

//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"body\" : \"?\"}}}}")
    List<DocBean> findByBody(String body, Pageable pageable);

//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"keyWords.keyword\" : \"?\"}}}}")
    List<DocBean> findByKeyWords(String keyWord, Pageable pageable);

//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"author.keyword\" : \"?\"}}}}")
    List<DocBean> findByAuthor(String author, Pageable pageable);

//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"members.keyword\" : \"?\"}}}}")
    List<DocBean> findByMembers(String members, Pageable pageable);

}
