package com.fulltext.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.fulltext.project.dao.DocumentInfoMapper;
import com.fulltext.project.entity.DocumentInfo;
import com.fulltext.project.entity.OrderIdSeq;
import com.fulltext.project.service.DocumentInfoService;
import com.fulltext.project.service.OrderIdSeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/2/3.
 *
 * @author anlu.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private OrderIdSeqService orderIdSeqService;

    @Autowired
    private DocumentInfoService documentInfoService;

    @RequestMapping("/testSelect")
    public String testSelect(){
        DocumentInfo.builder().build();
        orderIdSeqService.insert(new OrderIdSeq());
        return "success";
    }



}
