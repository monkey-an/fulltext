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
//        elasticService.createIndex();

        List<String> keyWords = new ArrayList<>();
        keyWords.add("制度");
        keyWords.add("粮食收储");
        keyWords.add("改革");

        List<String> members = new ArrayList<>();
        members.add("董淑广");
        members.add("韩峰");
        members.add("孙燕");

        String body = "西游记第一回：灵根育孕源流出，心性修持大道生。东胜神洲傲来国海中有花果山，山项上一仙石孕育出一石猴。石猴在所居涧水源头寻到名为“水帘洞”的石洞，被群猴拥戴为王。\n" +
                "又过三五百年，石猴忽为人生无常，不得久寿而悲啼。根据一老猴指点，石猴经南赡训洲到西牛贺洲，上灵台方寸山，入斜月三星洞，拜见须菩提祖师，被收为徒，起名曰孙悟空。\n" +
                "《西游记》是中国古代第一部浪漫主义章回体长篇神魔小说。这部小说以“唐僧取经”这一历史事件为蓝本，通过作者的艺术加工，深刻地描绘了当时的社会现实。\n" +
                "全书主要描写了孙悟空出世及大闹天宫后，遇见了唐僧、猪八戒、沙僧和白龙马，西行取经，一路降妖伏魔，经历了九九八十一难，终于到达西天见到如来佛祖，最终五圣成真的故事。\n" +
                "《西游记》是中国文学史上一部最杰出的充满奇思异想的神魔小说。作者吴承恩运用浪漫主义手法，翱翔着无比丰富的想象的翅膀，描绘了一个色彩缤纷、神奇瑰丽的幻想世界，创造了一系列妙趣横生、引人入胜的神话故事，成功地塑造了孙悟空这个超凡入圣的理想化的英雄形象。\n" +
                "《西游记》以它独特的思想和艺术魅力，把读者带进了美丽的艺术殿堂，感受其艺术魅力。\n" +
                "《西游记》的艺术魅力，除了它的奇异想象，就要数它的趣味了。在中国古典小说中，《西游记》可以说是趣味性和娱乐性最强的一部作品。\n" +
                "虽然取经路上尽是险山恶水，妖精魔怪层出不穷，充满刀光剑影，孙悟空的胜利也来之不易，但读者的阅读感受总是轻松的，充满愉悦而一点没有紧张感和沉重感。";
        DocBean doc_1  = new DocBean("1-2",
                "深化粮食收储制度改革政策措施研究",
                "粮食收储制度改革是农业供给侧结构性改革的重要内容，关系保障国家粮食安全，关系维护种粮农民利益，关系全面实施乡村振兴战略。在新时代，深化粮食收储制度改革，完善粮食收储政策措施，对于构建我国粮食安全保障体系，具有非常重要的战略意义和现实价值",
                 body,
                 keyWords,
                 "夏春胜",
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

}
