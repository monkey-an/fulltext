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
        elasticService.createIndex();
        List<DocBean> list =new ArrayList<>();

        List<String> keyWords = new ArrayList<>();
        keyWords.add("制度");
        keyWords.add("粮食收储");
        keyWords.add("改革");

        List<String> members = new ArrayList<>();
        members.add("董淑广");
        members.add("韩峰");
        members.add("孙燕");

        String body = "建国后，我国长期实行粮食统购统销制度。改革开放以来，先后实施了粮食统购统销、双轨制、合同定购、保护价收购、最低收购价收购等政策措施，粮食购销逐步向全面开放的粮食市场转变。上世纪九十年代，我国粮食生产出现波动。在 1998 年创造历史最高产量之后， 全国粮食生产连续5年减产，2003年粮食产量仅 8600亿斤，难以满足消费需求。在此背景下，2004年初，中央时隔17年再次发布“三农”问题一号文件，宣布对市场紧缺的主要粮食实行最低收购价等政策，调动农民的种粮积极性1。同年， 国务院《关于进一步深化粮食流通体制改革的意见》(国发〔2004〕17 号)提出， 当粮食供求发生重大变化时，为保证市场供应、保护农民利益，必要时可由国务院决定对短缺的重点粮食品种，在粮食主产区实行最低收购价格。至此，粮食最低收购价政策正式出台。国家 2004 年规定稻谷最低收购价格，2006 年将小麦纳 入最低收购价范围，2007 年出台玉米临时收储政策，2008 年对大豆实行临时收储。临时收储政策类似于最低收购价政策，它们共同构成我国粮食政策性收储制度。";
        DocBean doc_1  = new DocBean("1-2",
                "深化粮食收储制度改革政策措施研究",
                "粮食收储制度改革是农业供给侧结构性改革的重要内容，关系保障国家粮食安全，关系维护种粮农民利益，关系全面实施乡村振兴战略。在新时代，深化粮食收储制度改革，完善粮食收储政策措施，对于构建我国粮食安全保障体系，具有非常重要的战略意义和现实价值",
                 body,
                 keyWords,
                 "夏春胜",
                 members);
//        list.add(new DocBean(2L,"XX0210","XX7475","xxxxxxxxxx",1));
//        list.add(new DocBean(3L,"XX0257","XX8097","xxxxxxxxxxxxxxxxxx",1));
        elasticService.save(doc_1);
        return elasticService.extractKeyword(body, 5);
    }

    @GetMapping("/all")
    public Iterator<DocBean> all(){
        return elasticService.findAll();
    }

    @GetMapping("/findByTitle")
    public List<DocBean> findByTitle(@RequestParam(value = "word", defaultValue = "改革")  String word){
        return elasticService.findByTitle(word);
    }

}
