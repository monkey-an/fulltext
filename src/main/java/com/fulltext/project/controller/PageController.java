package com.fulltext.project.controller;

import com.fulltext.project.bo.*;
import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.constants.ElementTypeEnum;
import com.fulltext.project.elastic.entity.DocBean;
import com.fulltext.project.elastic.service.ElasticsearchService;
import com.fulltext.project.entity.*;
import com.fulltext.project.service.*;
import com.fulltext.project.util.FileUtil;
import com.fulltext.project.util.PageVoUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

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
public class PageController {

    @Autowired
    private DocumentInfoService documentInfoService;

    @Autowired
    private DocumentMenuService documentMenuService;

    @Autowired
    private DocumentStorageService documentStorageService;

    @Autowired
    private DocumentDetailService documentDetailService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeFlowService noticeFlowService;

    @Autowired
    private NoticeAttachmentService noticeAttachmentService;

    @Autowired
    private ElasticsearchService elasticsearchService;

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

        List<Notice> noticeList = noticeService.selectNoticeInnerOneMonth();

        if(user==null || (userRole !=null && userRole.getRoleId().equals(3))){
            //过滤掉只能内部看的
            noticeList = noticeList.stream().filter(notice -> !notice.getNoticeType().equals(1)).collect(Collectors.toList());
        }

        if(noticeList != null && noticeList.size()>0) {
            model.addAttribute("noticeList", noticeList);
        }

        return "hello";
    }

    @RequestMapping("/noticeDetail")
    public String noticeDetail(HttpServletRequest request,Model model){
        User user = (User)request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        UserRole userRole = (UserRole)request.getSession().getAttribute(ConstantValue.USER_ROLE_SESSION_KEY);

        boolean ifInnerUser = userRole!=null && !userRole.getRoleId().equals(3);

        Long noticeId = Long.parseLong(request.getParameter("noticeId"));

        Notice notice = noticeService.selectNoticeById(noticeId);

        if(!ifInnerUser && notice.getNoticeType().equals(1)){
            //无权限查看
        }else {
            model.addAttribute("notice", notice);

            List<NoticeAttachment> attachmentList = noticeAttachmentService.selectNoticeAttachmentByNoticeId(noticeId);
            if(attachmentList!=null && attachmentList.size()>0){
                model.addAttribute("attachmentList", attachmentList);
            }
        }

        return "notice_detail";
    }

    @RequestMapping("noticeAttachmentDownload")
    @ResponseBody
    public void noticeAttachmentDownload(HttpServletRequest request,HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute(ConstantValue.USER_SESSION_KEY);
        UserRole userRole = (UserRole)request.getSession().getAttribute(ConstantValue.USER_ROLE_SESSION_KEY);
        boolean ifInnerUser = userRole!=null && !userRole.getRoleId().equals(3);

        Long attachmentId = Long.parseLong(request.getParameter("attachmentId"));

        NoticeAttachment noticeAttachment = noticeAttachmentService.selectNoticeAttachmentById(attachmentId);
        Notice notice = noticeService.selectNoticeById(noticeAttachment.getNoticeId());

        if(!ifInnerUser && notice.getNoticeType().equals(1)){
            //无权限下载
        }else{
            String filePath = noticeAttachment.getAttachmentUrl();
            String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
            String type = new MimetypesFileTypeMap().getContentType(filePath);
            // 设置contenttype，即告诉客户端所发送的数据属于什么类型
            response.setHeader("Content-type",type);
            // 设置编码
            String realFileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
            // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
            response.setHeader("Content-Disposition", "attachment;filename=" + realFileName);
            FileUtil.download(filePath, response);
        }
        return ;
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
        DocumentMenu documentMenu = null;

        if(paramMap.containsKey("documentId")){
            documentIdStr = paramMap.get("documentId")[0];
        }else if(paramMap.containsKey("menuId")){
            menuIdStr = paramMap.get("menuId")[0];
            documentMenu = documentMenuService.selectDocumentMenuById(Long.parseLong(menuIdStr));
            if(documentMenu!=null){
                documentIdStr = documentMenu.getDocumentId()+"";
            }
        }

        DocumentInfo documentInfo = null;
        DocumentDetail documentDetail = null;
        String documentBookImage = "";
        if(!StringUtils.isEmpty(documentIdStr) && !StringUtils.isEmpty(menuIdStr)){
            //说明是两个参数都有，要下载章节内容
            documentInfo = documentInfoService.selectDocumentInfoByDocumentId(Long.parseLong(documentIdStr));
            documentDetail = documentDetailService.selectDocumentDetailByDocumentAndMenuId(Long.parseLong(documentIdStr),Long.parseLong(menuIdStr));
        }else if (!StringUtils.isEmpty(documentIdStr) && StringUtils.isEmpty(menuIdStr)){
            //说明是要下载整篇文章
            documentInfo = documentInfoService.selectDocumentInfoByDocumentId(Long.parseLong(documentIdStr));
            documentDetail = documentDetailService.selectDocumentDetailByDocumentId(Long.parseLong(documentIdStr));
        }


        DocumentStorage documentStorage = documentStorageService.getDocumentStorageByDocumentIdAndElementType(Long.parseLong(documentIdStr), ElementTypeEnum.BOOK_IMAGE.value);
        documentBookImage = documentStorage.getElementPath();

        model.addAttribute("documentInfo",documentInfo);
        model.addAttribute("documentDetail",documentDetail);
        model.addAttribute("documentBookImage",documentBookImage);
        model.addAttribute("documentIdStr",documentIdStr);
        model.addAttribute("menuIdStr",menuIdStr);
        model.addAttribute("documentMenu",documentMenu);


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
    public String search(HttpServletRequest request,Model model,@RequestParam(value = "pageNo", required = true, defaultValue = "1") int pageNo,
                         @RequestParam(value = "pageSize", required = true, defaultValue = "5") int pageSize,
                         @RequestParam(value = "searchKey", required = false) String searchKey,
                         @RequestParam(value = "searchValue", required = false) String searchValue,
                         @RequestParam(value = "searchWords", required = false) String searchWords){

        model.addAttribute("searchWords",searchWords);
        model.addAttribute("searchKey",searchKey);
        model.addAttribute("searchValue",searchValue);

        PageInfo<DocumentDetail> pageInfo = documentInfoService.selectUserSearchDocumentByPaging(pageNo, pageSize, searchKey, searchValue, searchWords);
        PageBean<DocumentDetail> pageBean = PageVoUtils.convertTopageVo(pageInfo);
        documentInfoService.addDocumentInfo(pageBean.getRows());
        pageBean.setCurrentPage(pageNo);
        pageBean.setPageSize(pageSize);

        model.addAttribute("pageBean",pageBean);

        return "search_result";
    }

    @RequestMapping("charge")
    public String charge(){
        return "charge";
    }
}
