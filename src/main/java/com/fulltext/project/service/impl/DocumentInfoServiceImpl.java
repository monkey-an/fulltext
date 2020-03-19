package com.fulltext.project.service.impl;

import com.fulltext.project.bo.*;
import com.fulltext.project.constants.ElementTypeEnum;
import com.fulltext.project.dao.DocumentInfoMapper;
import com.fulltext.project.dao.DocumentStorageMapper;
import com.fulltext.project.elastic.entity.DocBean;
import com.fulltext.project.elastic.service.ElasticsearchService;
import com.fulltext.project.entity.*;
import com.fulltext.project.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class DocumentInfoServiceImpl implements DocumentInfoService {
    @Resource
    private DocumentInfoMapper documentInfoMapper;

    @Resource
    private DocumentStorageService documentStorageService;

    @Resource
    private DocumentMenuService documentMenuService;

    @Resource
    private DocumentDetailService documentDetailService;

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Autowired
    private DocumentIdSeqService documentIdSeqService;


    @Override
    public DocumentInfo selectDocumentInfoByDocumentId(Long documentId) {
        return documentInfoMapper.selectByDocumentId(documentId);
    }

    @Override
    public List<DocumentInfo> selectDocumentInfoListByDocumentIdList(List<Long> documentIdList) {
        return documentInfoMapper.selectDocumentInfoListByDocumentIdList(documentIdList);
    }

    @Override
    public int insert(DocumentInfo entity) {
        return documentInfoMapper.insert(entity);
    }

    @Override
    public int update(DocumentInfo entity) {
        return documentInfoMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<DocumentSerialBO> loadDefaultDocumentInfoBySerialName(List<String> serialNameList) {
        List<DocumentInfo> documentInfoList = documentInfoMapper.selectBySerialNameList(serialNameList);

        List<DocumentSerialBO> documentSerialBOList = new ArrayList<>();
        serialNameList.forEach(serialName->{
            DocumentInfo documentInfo = documentInfoList.stream()
                    .filter(document->serialName.equals(document.getSerialName()))
                    .findFirst()
                    .orElse(null);

            if(documentInfo!=null){
                DocumentStorage imageStorage = documentStorageService.getDocumentStorageByDocumentIdAndElementType(documentInfo.getDocumentId(), ElementTypeEnum.BOOK_IMAGE.value);
                documentSerialBOList.add(DocumentSerialBO.builder()
                        .serialName(serialName)
                        .documentDesc(documentInfo.getDocumentDesc())
                        .documentSummary(documentInfo.getDocumentSummary())
                        .documentImagePath(imageStorage.getElementPath())
                        .build());
            }
        });

        return documentSerialBOList;
    }

    @Override
    public List<DocumentSerialDetailBO> loadDocumentInfoBySerialName(String serialName) {
        //根据系列名找出所有图书documentInfo
        //documentInfo按年份排序倒序
        //遍历，查询目录，加载目录
        //返回

        List<DocumentSerialDetailBO> documentSerialDetailBOList = new ArrayList<>();

        List<DocumentInfo> documentInfoList = documentInfoMapper.selectBySerialNameList(Arrays.asList(serialName));
        documentInfoList.sort(Comparator.comparing(DocumentInfo::getDocumentYear).reversed());

        documentInfoList.forEach(documentInfo -> {
            List<DocumentMenuNode> menuList = documentMenuService.getDocumentMenuListByDocumentId(documentInfo.getDocumentId());

            DocumentStorage imageStorage = documentStorageService.getDocumentStorageByDocumentIdAndElementType(documentInfo.getDocumentId(), ElementTypeEnum.BOOK_IMAGE.value);

            DocumentSerialDetailBO documentSerialDetailBO = new DocumentSerialDetailBO();
            documentSerialDetailBO.setDocumentInfo(documentInfo);
            documentSerialDetailBO.setDocumentMenuList(menuList);
            documentSerialDetailBO.setDocumentImagePath(imageStorage.getElementPath());
            documentSerialDetailBOList.add(documentSerialDetailBO);
        });

        return documentSerialDetailBOList;
    }

    @Override
    public Map<String, List<DocumentMenu>> flatRootMenuToMap(List<DocumentMenuNode> menuNodeList) {
        Map<String,List<DocumentMenu>> resultMap = new HashMap<>();
        menuNodeList.forEach(menuNode -> {
            if(!resultMap.containsKey(menuNode.getNode().getMenuName())){
                resultMap.put(menuNode.getNode().getMenuName(),new ArrayList<>());
            }
            flatMenu(resultMap,menuNode,menuNode.getNode().getMenuName());
        });
        return resultMap;
    }

    @Override
    public PageInfo<DocumentSearchBO> selectSearchDocumentByPaging(int pageNo, int pageSize, String documentName, String year) {
        List<DocumentInfo> documentInfoList = documentInfoMapper.selectAll(documentName,year);
        List<DocumentSearchBO> documentSearchBOList = new ArrayList<>();
        documentInfoList.forEach(documentInfo -> {
            List<DocumentMenuNode> menuList = documentMenuService.getDocumentMenuListByDocumentId(documentInfo.getDocumentId());
            documentSearchBOList.add(DocumentSearchBO.builder()
                    .documentId(documentInfo.getDocumentId())
                    .documentName(documentInfo.getDocumentName() + "-" +documentInfo.getDocumentYear())
                    .pageInfo(documentInfo.getDocumentTotalPage()+"")
                    .build());
            List<DocumentSearchBO> tempList = new ArrayList<>();
            createDocumentSearchBOList(documentInfo.getDocumentName() + "-" +documentInfo.getDocumentYear(),tempList,menuList);
            tempList.forEach(temp->temp.setDocumentName(documentInfo.getDocumentName() + "-" +documentInfo.getDocumentYear()));
            documentSearchBOList.addAll(tempList);
        });

//        PageHelper.startPage(pageNo, pageSize);
        List<DocumentSearchBO> resultList = new ArrayList<>();
        for(int i=(pageNo-1)*pageSize;i<(pageNo)*pageSize;++i){
            if(documentSearchBOList.size()>i) {
                resultList.add(documentSearchBOList.get(i));
            }else{
                break;
            }
        }
        PageInfo<DocumentSearchBO> info=new PageInfo<>(resultList);
        info.setTotal(documentSearchBOList.size());
        return info;
    }

    @Override
    public List<Map<String, Object>> getAllMenuNameMap() {
        List<Map<String,Object>> resultList = new ArrayList<>();
        List<DocumentInfo> documentInfoList = documentInfoMapper.selectAll(null,null);

        for (DocumentInfo documentInfo : documentInfoList) {
            Map<String,Object> tempMap = new HashMap<>();
            tempMap.put("documentId",documentInfo.getDocumentId());
            tempMap.put("menuName",documentInfo.getDocumentName()+"-"+documentInfo.getDocumentYear());
            tempMap.put("menuId",null);
            resultList.add(tempMap);

            List<DocumentMenuNode> menuList = documentMenuService.getDocumentMenuListByDocumentId(documentInfo.getDocumentId());
            for (DocumentMenuNode documentMenuNode : menuList) {
                flatMenuFullName(resultList,documentInfo.getDocumentName()+"-"+documentInfo.getDocumentYear(),documentMenuNode);
            }

        }
        return resultList;
    }

    private void flatMenuFullName(List<Map<String,Object>> resultList,String parentName,DocumentMenuNode node){
        node.getNode().setMenuName(parentName+"-"+node.getNode().getMenuName());

        Map<String,Object> tempMap = new HashMap<>();
        tempMap.put("documentId",node.getNode().getDocumentId());
        tempMap.put("menuName",node.getNode().getMenuName());
        tempMap.put("menuId",node.getNode().getId());
        resultList.add(tempMap);

        if(node.getSubNodeList()!=null&&node.getSubNodeList().size()>0){
            for (DocumentMenuNode documentMenuNode : node.getSubNodeList()) {
                flatMenuFullName(resultList,node.getNode().getMenuName(),documentMenuNode);
            }
        }
    }

    private void flatMenu(Map<String, List<DocumentMenu>> map,DocumentMenuNode menuNode,String key){
            if(menuNode.getSubNodeList()==null || menuNode.getSubNodeList().isEmpty()){
                map.get(key).add(menuNode.getNode());
            }else {
                menuNode.getSubNodeList().forEach(subNode->flatMenu(map,subNode,key));
            }
    }

    private void createDocumentSearchBOList(String documentName,List<DocumentSearchBO> documentSearchBOList,List<DocumentMenuNode> menuList){
        for (DocumentMenuNode documentMenuNode : menuList) {
            createDocumentSearchBOList(documentName,documentSearchBOList,documentMenuNode);
        }
    }

    private void createDocumentSearchBOList(String fullMenuName,List<DocumentSearchBO> documentSearchBOList,DocumentMenuNode menu){
        if(menu.getSubNodeList()==null||menu.getSubNodeList().size()==0){
            //create
            documentSearchBOList.add(DocumentSearchBO.builder()
                    .menuFullName(fullMenuName+"-"+menu.getNode().getMenuName())
                    .menuId(menu.getNode().getId())
                    .menuName(menu.getNode().getMenuName())
                    .pageInfo(menu.getNode().getMenuPage())
                    .documentId(menu.getNode().getDocumentId())
                    .build());
        }else{
            for (DocumentMenuNode documentMenuNode : menu.getSubNodeList()) {
                createDocumentSearchBOList(fullMenuName+"-"+menu.getNode().getMenuName(),documentSearchBOList,documentMenuNode);
            }
        }
    }


    @Override
    public void createDocumentSearch(String documentMenuId, String content) {
        String[] menuInfoStrArr = documentMenuId.split("-");
        DocumentInfo documentInfo = documentInfoMapper.selectByDocumentId(Long.parseLong(menuInfoStrArr[0]));
        DocumentDetail documentDetail = null;
        if ("null".equals(menuInfoStrArr[1])) {
            documentDetail = documentDetailService.selectDocumentDetailByDocumentId(Long.parseLong(menuInfoStrArr[0]));
        } else {
            documentDetail = documentDetailService.selectDocumentDetailByDocumentAndMenuId(Long.parseLong(menuInfoStrArr[0]), Long.parseLong(menuInfoStrArr[1]));
        }

        List<String> members = new ArrayList<>();
        members.add("董淑广");
        members.add("韩峰");
        members.add("孙燕");
        members.add("吴承恩");

        List<String> keyWords = null;
        if(StringUtils.isNotEmpty(documentDetail.getKeyWords())) {
            keyWords = new ArrayList<>(Arrays.asList(documentDetail.getKeyWords().split(",")));
        }else{
            keyWords = new ArrayList<>();
        }

        DocBean docBean = DocBean.builder()
                .author(documentDetail.getAuthor())
                .members(members)
                .body(content)
                .businessId(documentMenuId)
                .keyWords(keyWords)
                .summary(documentDetail.getSummary())
                .title(documentInfo.getDocumentName())
                .build();

        List<String> resultKeyWordList = elasticsearchService.saveReturnKeywords(docBean, 10);
        StringBuilder sb = new StringBuilder();
        resultKeyWordList.forEach(keyWord -> sb.append(keyWord + ","));
        String keyWordStr = sb.substring(0, sb.length() - 1);
        documentDetail.setKeyWords(keyWordStr);

        documentDetailService.update(documentDetail);

    }

    @Override
    public PageInfo<DocumentInfo> selectUserSearchDocumentByPaging(int pageNo, int pageSize, String searchValue, String searchWords) {
        List<DocBean> docBeanList = null;

        switch (searchValue){
            case "全文":
                docBeanList = elasticsearchService.findByBody(searchWords);
                break;
            case "标题":
                docBeanList = elasticsearchService.findByTitle(searchWords);
                break;
            case "关键字":
                docBeanList = elasticsearchService.findByKeyWords(searchWords);
                break;
            case "第一作者":
                docBeanList = elasticsearchService.findByAuthor(searchWords);
                break;
            case "第二作者":
                docBeanList = elasticsearchService.findByMembers(searchWords);
                break;
            default:
                break;
        }

        if (docBeanList == null || docBeanList.size() == 0) {
            //没查到
        }
        else {
            List<String> businessIdList = docBeanList.stream().map(DocBean::getBusinessId).collect(Collectors.toList());
            Set<Long> documentIdSet = new HashSet<>();
            Set<Long> menuIdSet = new HashSet<>();
            businessIdList.forEach(businessId->{
                String[] arr = businessId.split("-");
                //"null-1"
                if(!"null".equals(arr[0])){
                    documentIdSet.add(Long.parseLong(arr[0]));
                }
                if(!"null".equals(arr[1])){
                    menuIdSet.add(Long.parseLong(arr[1]));
                }
            });

            List<DocumentMenu> menuList = documentMenuService.selectDocumentMenuListByIdList(new ArrayList<>(menuIdSet));
            if(menuList!=null && menuList.size()>0){
                documentIdSet.addAll(menuList.stream().map(DocumentMenu::getDocumentId).collect(Collectors.toList()));
            }

            List<DocumentInfo> documentInfoList = selectDocumentInfoListByDocumentIdList(new ArrayList<>(documentIdSet));


            List<DocumentInfo> resultList = new ArrayList<>();
            for(int i=(pageNo-1)*pageSize;i<(pageNo)*pageSize;++i){
                if(documentInfoList.size()>i) {
                    resultList.add(documentInfoList.get(i));
                }else{
                    break;
                }
            }
            PageInfo<DocumentInfo> info=new PageInfo<>(resultList);
            info.setTotal(documentInfoList.size());
            return info;
        }
        return new PageInfo<>();
    }

    @Override
    public boolean addDocument(HttpServletRequest request) {
        String documentName = request.getParameter("document-name-input");
        String documentType = request.getParameter("documentType");
        String serialName = request.getParameter("serial-name-input");
        String author = request.getParameter("document-author-input");
        String documentMembers = request.getParameter("document-members-input");
        String publisher = request.getParameter("document-publisher-input");
        String year = request.getParameter("document-year-input");
        String isbn = request.getParameter("document-isbn-input");
        String totalPageStr = request.getParameter("document-total-page-input");
        String totalCountStr = request.getParameter("document-total-count-input");
        String desc = request.getParameter("document-desc-input");
        String summary = request.getParameter("document-summary-input");
        String priceStr = request.getParameter("document-price-input");

        DocumentIdSeq idSeq = new DocumentIdSeq();
        documentIdSeqService.insert(idSeq);

        //写documentInfo
        DocumentInfo documentInfo = DocumentInfo.builder()
                .documentId(idSeq.getId())
                .documentName(documentName)
                .documentTypeId(Long.parseLong(documentType))
                .serialName(serialName)
                .documentAuthor(author)
                .documentYear(year)
                .documentIsbn(isbn)
                .documentTotalPage(Integer.parseInt(totalPageStr))
                .documentDesc(desc)
                .documentSummary(summary)
                .documentPrice(BigDecimal.valueOf(Float.parseFloat(priceStr)))
                .createTime(new Date())
                .updateTime(new Date())
                .documentTotalCount(Integer.parseInt(totalCountStr))
                .documentPublisher(publisher)
                .documentMembers(documentMembers)
                .status(1)
                .build();

        //写documentDetail
        DocumentDetail documentDetail = DocumentDetail.builder()
                .author(author)
                .documentId(idSeq.getId())
                .createTime(new Date())
                .status(1)
                .summary(summary)
                .updateTime(new Date())
                .build();

        documentDetailService.insert(documentDetail);

        int count = insert(documentInfo);
        return count>0;
    }

    @Override
    public boolean addMenu(HttpServletRequest request) {
        String documentId = request.getParameter("documentId");
        String menuId = request.getParameter("menuId");
        String documentMenuName = request.getParameter("document-menu-name-input");
        String documentMenuPage = request.getParameter("document-menu-page-input");
        String documentMenuAuthor = request.getParameter("document-menu-author-input");
        String documengMenuSumaary = request.getParameter("document-menu-summary-input");
        String documentMenuKeywords = request.getParameter("document-menu-keywords-input");

        DocumentInfo documentInfo = selectDocumentInfoByDocumentId(Long.parseLong(documentId));
        DocumentDetail documentWholeDetail = documentDetailService.selectDocumentDetailByDocumentId(Long.parseLong(documentId));

        //写menu信息
        DocumentMenu documentMenu = DocumentMenu.builder()
                .documentId(Long.parseLong(documentId))
                .parentMenuId(!"null".equals(menuId)?Long.parseLong(menuId):null)
                .createTime(new Date())
                .menuName(documentMenuName)
                .menuPage(documentMenuPage)
                .updateTime(new Date())
                .build();

        int count = documentMenuService.insert(documentMenu);

        //写documentDetail信息
        DocumentDetail documentDetail = DocumentDetail.builder()
                .author(StringUtils.isNotEmpty(documentMenuAuthor)?documentMenuAuthor:documentInfo.getDocumentAuthor())
                .documentId(Long.parseLong(documentId))
                .createTime(new Date())
                .status(1)
                .summary(documengMenuSumaary)
                .menuId(documentMenu.getId())
                .keyWords(StringUtils.isNotEmpty(documentMenuKeywords)?documentMenuKeywords:documentWholeDetail.getKeyWords())
                .updateTime(new Date())
                .build();
        count += documentDetailService.insert(documentDetail);

        return count>1;
    }
}
