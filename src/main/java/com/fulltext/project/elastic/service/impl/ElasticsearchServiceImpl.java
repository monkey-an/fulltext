package com.fulltext.project.elastic.service.impl;

import com.fulltext.project.elastic.dao.ElasticsearchDao;
import com.fulltext.project.elastic.entity.DocBean;
import com.fulltext.project.elastic.service.ElasticsearchService;
import com.hankcs.hanlp.HanLP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
@Slf4j
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
    public List<String> extractKeyword(String content, int topk) {
        return HanLP.extractKeyword(content, topk);
    }

    @Override
    public List<String> saveReturnKeywords(DocBean docBean, int topK) {
        String body = docBean.getBody();
        List<String> hanlp_kws = extractKeyword(body, topK);
        List<String> kws  = docBean.getKeyWords();
        log.info(kws.toString());
        for (String word: hanlp_kws) {
                if (!kws.contains(word)){
                    kws.add(word);
                }
        }

        docBean.setKeyWords(kws);
        elasticsearchDao.save(docBean);
        return kws.subList(0, topK);
    }

    @Override
    public List<DocBean> findByBody(String body) {
        return elasticsearchDao.findByBody(body, null);
    }

}
