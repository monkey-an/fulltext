package com.fulltext.project.controller;

import com.fulltext.project.elastic.entity.DocBean;
import com.fulltext.project.elastic.service.ElasticsearchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tiefanding on 2020/3/14.
 */

@Log4j2
@RestController
@RequestMapping("/elastic")
public class ElasticController {
    @Autowired
    private ElasticsearchService elasticService;

    @GetMapping("/init")
    public List<String> init(){

        List<String> keyWords = new ArrayList<>();
        keyWords.add("制度");
        keyWords.add("粮食收储");
        keyWords.add("改革");

        List<String> members = new ArrayList<>();
        members.add("董淑广");
        members.add("韩峰");
        members.add("孙燕");

        String body = "中华人民共和国";
        DocBean doc_1  = new DocBean("1-3",
                "深化粮食收储制度改革政策措施研究",
                "粮食收储制度改革是农业供给侧结构性改革的重要内容，关系保障国家粮食安全，关系维护种粮农民利益，关系全面实施乡村振兴战略。在新时代，深化粮食收储制度改革，完善粮食收储政策措施，对于构建我国粮食安全保障体系，具有非常重要的战略意义和现实价值",
                 body,
                 keyWords,
                 "中华人民共和国",
                 members);

        List<String> kws = elasticService.saveReturnKeywords(doc_1, 10);
        return kws;
    }

    @GetMapping("/all")
    public Iterator<DocBean> all(){
        return elasticService.findAll();
    }

    @GetMapping("/findByTitle")
    public List<DocBean> findByTitle(@RequestParam(value = "word", defaultValue = "改革")  String word){
        return elasticService.findByTitle(word);
    }

    @GetMapping("/matchQuery")
    public List<DocBean> matchQuery(@RequestParam(value = "query", defaultValue = "改革")  String query,
                                    @RequestParam(value = "field", defaultValue = "title") String field){
        return elasticService.matchQuery(query, field);
    }


    @GetMapping("/delete_index")
    public Boolean deleteIndex(@RequestParam(value = "index", defaultValue = "book")  String index_name){
        return elasticService.deleteIndex(index_name);
    }

    @GetMapping("/create_index")
    public Boolean createIndex(){
        return elasticService.createIndex();
    }


    @GetMapping("/put_mapping")
    public Boolean putMapping(){
        return elasticService.putMapping();
    }

}
