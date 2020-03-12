//package com.fulltext.project.elastic.dao;
//
//import com.fulltext.project.elastic.entity.DocBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.annotations.Query;
//import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//
///**
// * Description
// * <p>
// * </p>
// * DATE 2020/3/12.
// *
// * @author anlu.
// */
//public interface XXXDao extends ElasticsearchRepository<DocBean, Long> {
//    //默认的注释
//    //@Query("{\"bool\" : {\"must\" : {\"field\" : {\"content\" : \"?\"}}}}")
//    Page<DocBean> findByContent(String content, Pageable pageable);
//
//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"firstCode.keyword\" : \"?\"}}}}")
//    Page<DocBean> findByFirstCode(String firstCode, Pageable pageable);
//
//    @Query("{\"bool\" : {\"must\" : {\"field\" : {\"secordCode.keyword\" : \"?\"}}}}")
//    Page<DocBean> findBySecordCode(String secordCode, Pageable pageable);
//}
