package com.fulltext.project.controller;

import com.fulltext.project.bo.DocumentSearchBO;
import com.fulltext.project.bo.PageBean;
import com.fulltext.project.constants.ConstantValue;
import com.fulltext.project.entity.TaskAttachment;
import com.fulltext.project.entity.User;
import com.fulltext.project.service.DocumentInfoService;
import com.fulltext.project.service.NoticeService;
import com.fulltext.project.service.UserRoleService;
import com.fulltext.project.service.UserService;
import com.fulltext.project.util.FileUploadUtil;
import com.fulltext.project.util.PageVoUtils;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description
 * <p>
 * </p>
 * DATE 2020/3/9.
 *
 * @author anlu.
 */
@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private DocumentInfoService documentInfoService;

    @Autowired
    private NoticeService noticeService;


    @RequestMapping("")
    public String innerIndex() {
        return "admin/admin_index";
    }

    @RequestMapping("/getMainPage")
    public String getMainPage(HttpServletRequest request) {
        String funName = request.getParameter("funName");
        String resultView = "";
        if (StringUtils.isNotEmpty(funName)) {
            switch (funName) {
                case "admin-docuemnt-input":
                    resultView = "admin/add_document";
                    break;
                case "admin-user-manage":
                    resultView = "admin/manage_user";
                    break;
                case "admin-department-manage":
                    resultView = "admin/manage_department";
                    break;
                case "admin-search-input":
                    resultView = "admin/manage_search";
                    break;
                case "admin-add-notice":
                    resultView = "admin/add_notice";
                    break;
                default:
                    break;
            }
        }
        return resultView;
    }

    @RequestMapping("/getAllUserByPaging")
    @ResponseBody
    public PageBean<User> getAllUserByPaging(@RequestParam(value = "pageNo", required = true, defaultValue = "1") int pageNo,
                                             @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
                                             @RequestParam(value = "nickName", required = false) String nickName,
                                             @RequestParam(value = "email", required = false) String email) {
        if (StringUtils.isEmpty(nickName)) {
            nickName = null;
        }
        if (StringUtils.isEmpty(email)) {
            email = null;
        }
        PageInfo<User> pageInfo = userService.selectAllUserByPaging(pageNo, pageSize, nickName, email);
        PageBean<User> pageBean = PageVoUtils.convertTopageVo(pageInfo);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(pageNo);
        return pageBean;
    }

    @RequestMapping("/getAllSearchDocument")
    @ResponseBody
    public PageBean<DocumentSearchBO> getAllSearchDocumentByPaging(@RequestParam(value = "pageNo", required = true, defaultValue = "1") int pageNo,
                                                                   @RequestParam(value = "pageSize", required = true, defaultValue = "10") int pageSize,
                                                                   @RequestParam(value = "documentName", required = false) String documentName,
                                                                   @RequestParam(value = "year", required = false) String year) {
        if (StringUtils.isEmpty(documentName)) {
            documentName = null;
        }
        if (StringUtils.isEmpty(year)) {
            year = null;
        }
        PageInfo<DocumentSearchBO> pageInfo = documentInfoService.selectSearchDocumentByPaging(pageNo, pageSize, documentName, year);
        PageBean<DocumentSearchBO> pageBean = PageVoUtils.convertTopageVo(pageInfo);
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(pageNo);
        return pageBean;
    }

    @RequestMapping("/getAllMenuNameMap")
    @ResponseBody
    public List<Map<String, Object>> getAllMenuNameMap() {
        return documentInfoService.getAllMenuNameMap();
    }

    @RequestMapping("/createDocumentSearch")
    @ResponseBody
    public String createDocumentSearch(@RequestParam(value = "document_menu_id", required = true) String documentMenuId,
                                       @RequestParam(value = "content", required = true) String content) {
        documentInfoService.createDocumentSearch(documentMenuId, content);
        return "";
    }

    @RequestMapping("/document_add")
    @ResponseBody
    public String documentAdd(HttpServletRequest request) {
        return documentInfoService.addDocument(request) ? "SUCCESS" : "ERROR";
    }

    @RequestMapping("/menu_add")
    @ResponseBody
    public String menuAdd(HttpServletRequest request) {
        return documentInfoService.addMenu(request) ? "SUCCESS" : "ERROR";
    }

    @RequestMapping("/notice_add")
    @ResponseBody
    public String noticeAdd(HttpServletRequest request){
        return noticeService.addNotice(request);
    }

}
