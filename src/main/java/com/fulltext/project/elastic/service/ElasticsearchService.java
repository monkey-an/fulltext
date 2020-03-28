package com.fulltext.project.elastic.service;

import com.fulltext.project.elastic.entity.DocBean;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/12.
 *
 * @author anlu.
 */
public interface ElasticsearchService {
    Boolean createIndex();

    boolean deleteIndex(String index);

    void save(DocBean docBean);

    void saveAll(List<DocBean> list);

    Iterator<DocBean> findAll();

    List<DocBean> findByTitle(String title);

    List<DocBean> findBySummary(String summary);

    List<DocBean> findByBody(String body);

    List<DocBean> findByKeyWords(String keyWord);

    List<DocBean> findByAuthor(String author);

    List<DocBean> findByMembers(String members);
    List<String> extractKeyword(String content, int topk);
    List<String> saveReturnKeywords(DocBean docBean, int topK);
    List<DocBean> matchQuery(String query, String fields);
    Boolean putMapping();
}
