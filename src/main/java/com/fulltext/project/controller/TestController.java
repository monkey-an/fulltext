package com.fulltext.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.fulltext.project.entity.OrderIdSeq;
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

    @RequestMapping("/testSelect")
    public String testSelect(){
        orderIdSeqService.insert(new OrderIdSeq());
        return "success";
    }

}
