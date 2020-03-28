package com.fulltext.project.elastic.service.impl;

import com.fulltext.project.elastic.dao.ElasticsearchDao;
import com.fulltext.project.elastic.entity.DocBean;
import com.fulltext.project.elastic.service.ElasticsearchService;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NotionalTokenizer;
import org.elasticsearch.index.query.QueryBuilders;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Boolean createIndex() {
        return elasticsearchRestTemplate.createIndex(DocBean.class);
    }

    @Override
    public boolean deleteIndex(String index) {
        return elasticsearchRestTemplate.deleteIndex(index);
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
        List<Term> segmentResult = NotionalTokenizer.segment(body);
        List<String> words= segmentResult.stream().map(term->term.word).collect(Collectors.toList());
        List<String> hanlp_kws = extractKeyword(words.toString(), topK);
        List<String> kws  = docBean.getKeyWords();

        for (String word: hanlp_kws) {
                if (!kws.contains(word)){
                    kws.add(word);
                }
        }

        docBean.setKeyWords(kws);
        elasticsearchDao.save(docBean);

        int kwsSize = kws.size();
        if(kwsSize < 10){
            topK = kwsSize;
        }
        return kws.subList(0, topK);
    }

    @Override
    public List<DocBean> matchQuery(String query, String field) {

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(
                QueryBuilders.matchQuery(field, query).analyzer("ik_max_word"));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(functionScoreQueryBuilder).withSort(SortBuilders.scoreSort()).build();


//        QueryBuilder queryBuilder = QueryBuilders.matchQuery(field, query).analyzer("ik_max_word");
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(queryBuilder).build();

        List<DocBean> rs = elasticsearchRestTemplate.queryForList(searchQuery, DocBean.class);

        return rs;
    }

    @Override
    public Map<String, Object> getMapping(String indexName, String type) {
        return elasticsearchRestTemplate.getMapping(indexName, type);
    }

    @Override
    public Boolean putMapping() {
        return elasticsearchRestTemplate.putMapping(DocBean.class);
    }

    @Override
    public List<DocBean> findByBody(String body) {
        return elasticsearchDao.findByBody(body, null);
    }

}
