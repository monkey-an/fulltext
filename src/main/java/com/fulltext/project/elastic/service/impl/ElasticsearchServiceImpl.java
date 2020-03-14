package com.fulltext.project.elastic.service.impl;

import com.fulltext.project.elastic.dao.ElasticsearchDao;
import com.fulltext.project.elastic.entity.DocBean;
import com.fulltext.project.elastic.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

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
@Service("ElasticsearchService")
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    private ElasticsearchDao elasticsearchDao;

    private Pageable pageable = PageRequest.of(0,10);

    @Override
    public void createIndex() {
        elasticsearchRestTemplate.createIndex(DocBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchRestTemplate.deleteIndex(index);
    }

    @Override
    public void save(DocBean docBean) {
        elasticsearchDao.save(docBean);
    }

    @Override
    public void saveAll(List<DocBean> list) {
        elasticsearchDao.saveAll(list);
    }

    @Override
    public Iterator<DocBean> findAll() {
        return elasticsearchDao.findAll().iterator();
    }

    @Override
    public List<DocBean> findByTitle(String title) {
        return elasticsearchDao.findByTitle(title,null);
    }

    @Override
    public List<DocBean> findBySummary(String summary) {
        return elasticsearchDao.findBySummary(summary,null);
    }

    @Override
    public List<DocBean> findByKeyWords(String keyWord) {
        return elasticsearchDao.findByKeyWords(keyWord,null);
    }

    @Override
    public List<DocBean> findByAuthor(String author) {
        return elasticsearchDao.findByAuthor(author, null);
    }

    @Override
    public List<DocBean> findByMembers(String members) {
        return elasticsearchDao.findByMembers(members, null);
    }

    @Override
    public List<DocBean> findByBody(String body) {
        return elasticsearchDao.findByBody(body, null);
    }

}
