//package com.fulltext.project.elastic.service;
//
//import com.fulltext.project.elastic.entity.DocBean;
//import org.springframework.data.domain.Page;
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
//public interface XXXService {
//    void createIndex();
//
//    void deleteIndex(String index);
//
//    void save(DocBean docBean);
//
//    void saveAll(List<DocBean> list);
//
//    Iterator<DocBean> findAll();
//
//    Page<DocBean> findByContent(String content);
//
//    Page<DocBean> findByFirstCode(String firstCode);
//
//    Page<DocBean> findBySecordCode(String secordCode);
//
//    Page<DocBean> query(String key);
//}
