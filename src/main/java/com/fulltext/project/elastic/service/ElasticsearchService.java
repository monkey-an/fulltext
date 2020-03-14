package com.fulltext.project.elastic.service;

import com.fulltext.project.elastic.entity.DocBean;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/12.
 *
 * @author anlu.
 */
public interface ElasticsearchService {
    void createIndex();

    void deleteIndex(String index);

    void save(DocBean docBean);

    void saveAll(List<DocBean> list);

    Iterator<DocBean> findAll();

    List<DocBean> findByTitle(String title);

    List<DocBean> findBySummary(String summary);

    List<DocBean> findByBody(String body);

    List<DocBean> findByKeyWords(String keyWord);

    List<DocBean> findByAuthor(String author);

    List<DocBean> findByMembers(String members);

}
