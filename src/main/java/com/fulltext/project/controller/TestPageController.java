package com.fulltext.project.controller;

import com.fulltext.project.bo.DocumentMenuNode;
import com.fulltext.project.bo.DocumentSerialBO;
import com.fulltext.project.bo.DocumentSerialDetailBO;
import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.constants.ElementTypeEnum;
import com.fulltext.project.entity.*;
import com.fulltext.project.service.DocumentInfoService;
import com.fulltext.project.service.DocumentMenuService;
import com.fulltext.project.service.DocumentStorageService;
import com.fulltext.project.util.FileUtil;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/2/3.
 *
 * @author anlu.
 */

@Controller
@RequestMapping("/")
@Slf4j
public class TestPageController {

    @Autowired
    private DocumentInfoService documentInfoService;

    @Autowired
    private DocumentMenuService documentMenuService;

    @Autowired
    private DocumentStorageService documentStorageService;

    @RequestMapping("")
    public String helloPage(HttpServletRequest request,Model model){
        List<String> needLoadDocumentSerialList = new ArrayList<>();
        needLoadDocumentSerialList.add("获奖汇编");
        needLoadDocumentSerialList.add("发展报告");
        needLoadDocumentSerialList.add("年鉴");

        List<DocumentSerialBO> documentSerialBOList = documentInfoService.loadDefaultDocumentInfoBySerialName(needLoadDocumentSerialList);
        model.addAttribute("documentSerialBOList",documentSerialBOList);

        User user = (User)request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        UserRole userRole = (UserRole)request.getSession().getAttribute(ConstantValue.USER_ROLE_SESSION_KEY);
        model.addAttribute("user", user);
        model.addAttribute("userRole", userRole);

        return "hello";
    }

    @RequestMapping("detail")
    public String detailPage(HttpServletRequest request,Model model){
        Map<String,String[]> paramMap = request.getParameterMap();
        String serialName = paramMap.get("serial")[0];

        List<DocumentSerialDetailBO> documentSerialDetailBOList = documentInfoService.loadDocumentInfoBySerialName(serialName);
        model.addAttribute("boList",documentSerialDetailBOList);

        List<DocumentMenuNode> rootMenuNodeList = new ArrayList<>();
        documentSerialDetailBOList.forEach(documentSerialDetailBO -> rootMenuNodeList.addAll(documentSerialDetailBO.getDocumentMenuList()));
        Map<String,List<DocumentMenu>> flatMenuMap = documentInfoService.flatRootMenuToMap(rootMenuNodeList);
        model.addAttribute("flatMenuMap",flatMenuMap);

        return "detail";
    }

    @RequestMapping("downLoadPage")
    public String downLoadPage(HttpServletRequest request,Model model){
        Map<String,String[]> paramMap = request.getParameterMap();
        String documentIdStr = "";
        String menuIdStr = "";

        if(paramMap.containsKey("documentId")){
            documentIdStr = paramMap.get("documentId")[0];
        }else if(paramMap.containsKey("menuId")){
            menuIdStr = paramMap.get("menuId")[0];
            DocumentMenu documentMenu = documentMenuService.selectDocumentMenuById(Long.parseLong(menuIdStr));
            if(documentMenu!=null){
                documentIdStr = documentMenu.getDocumentId()+"";
            }
        }

        DocumentInfo documentInfo = null;
        String documentBookImage = "";
        if(!StringUtils.isEmpty(documentIdStr) && !StringUtils.isEmpty(menuIdStr)){
            //说明是两个参数都有，要下载章节内容
            documentInfo = documentInfoService.selectDocumentInfoByDocumentId(Long.parseLong(documentIdStr));
        }else if (!StringUtils.isEmpty(documentIdStr) && StringUtils.isEmpty(menuIdStr)){
            //说明是要下载整篇文章
            documentInfo = documentInfoService.selectDocumentInfoByDocumentId(Long.parseLong(documentIdStr));
        }

        DocumentStorage documentStorage = documentStorageService.getDocumentStorageByDocumentIdAndElementType(Long.parseLong(documentIdStr), ElementTypeEnum.BOOK_IMAGE.value);
        documentBookImage = documentStorage.getElementPath();

        model.addAttribute("documentInfo",documentInfo);
        model.addAttribute("documentBookImage",documentBookImage);
        model.addAttribute("documentIdStr",documentIdStr);
        model.addAttribute("menuIdStr",menuIdStr);

        return "down_load";
    }

    @RequestMapping("downLoad")
    @ResponseBody
    public void downLoadPdf(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException {
        Map<String,String[]> paramMap = request.getParameterMap();
        String documentIdStr = "";
        String menuIdStr = "";
        String downLoadType = "";

        if(paramMap.containsKey("documentId")){
            documentIdStr = paramMap.get("documentId")[0];
        }
        if(paramMap.containsKey("menuId")){
            menuIdStr = paramMap.get("menuId")[0];
        }
        if(paramMap.containsKey("downLoadType")){
            downLoadType = paramMap.get("downLoadType")[0];
        }

        documentStorageService.downLoadDocument(documentIdStr,menuIdStr,downLoadType,response);

        return ;
    }

    @RequestMapping("search")
    public String search(){
        return "search_result";
    }

    @RequestMapping("charge")
    public String charge(){
        return "charge";
    }
}
