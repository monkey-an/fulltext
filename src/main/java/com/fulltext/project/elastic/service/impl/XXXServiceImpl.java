//package com.fulltext.project.elastic.service.impl;
//
//import com.fulltext.project.elastic.dao.XXXDao;
//import com.fulltext.project.elastic.entity.DocBean;
//import com.fulltext.project.elastic.service.XXXService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Description
// * <p>
// * </p>
// * DATE 2020/3/12.
// *
// * @author anlu.
// */
//public class XXXServiceImpl implements XXXService {
//
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//    @Autowired
//    private XXXDao xxxDao;
//
//    @Override
//    public void createIndex() {
//        elasticsearchRestTemplate.createIndex(DocBean.class);
//    }
//
//    @Override
//    public void deleteIndex(String index) {
//        elasticsearchRestTemplate.deleteIndex(index);
//    }
//
//    @Override
//    public void save(DocBean docBean) {
//        xxxDao.save(docBean);
//    }
//
//    @Override
//    public void saveAll(List<DocBean> list) {
//        xxxDao.saveAll(list);
//    }
//
//    @Override
//    public Iterator<DocBean> findAll() {
//        return xxxDao.findAll().iterator();
//    }
//
//    @Override
//    public Page<DocBean> findByContent(String content) {
//        return xxxDao.findByContent(content,null);
//    }
//
//    @Override
//    public Page<DocBean> findByFirstCode(String firstCode) {
//        return xxxDao.findByFirstCode(firstCode,null);
//    }
//
//    @Override
//    public Page<DocBean> findBySecordCode(String secordCode) {
//        return xxxDao.findBySecordCode(secordCode,null);
//    }
//
//    @Override
//    public Page<DocBean> query(String key) {
//        return xxxDao.findByContent(key, null);
//    }
//}
