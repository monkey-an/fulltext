package com.fulltext.project.service.impl;

import com.fulltext.project.bo.*;
import com.fulltext.project.constants.ElementTypeEnum;
import com.fulltext.project.dao.DocumentInfoMapper;
import com.fulltext.project.dao.DocumentStorageMapper;
import com.fulltext.project.entity.*;
import com.fulltext.project.service.DocumentDetailService;
import com.fulltext.project.service.DocumentInfoService;
import com.fulltext.project.service.DocumentMenuService;
import com.fulltext.project.service.DocumentStorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;


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

    @Override
    public DocumentInfo selectDocumentInfoByDocumentId(Long documentId) {
        return documentInfoMapper.selectByDocumentId(documentId);
    }

    @Override
    public List<DocumentInfo> selectDocumentInfoListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(DocumentInfo entity) {
        return 0;
    }

    @Override
    public int update(DocumentInfo entity) {
        return 0;
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
        //根据系列名找出所有文档documentInfo
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
        DocumentMenu documentMenu = null;
        DocumentDetail documentDetail = null;
        Map<String,String> params = new HashMap<>();
        if(StringUtils.isEmpty(menuInfoStrArr[1])){
            //整篇
            //es service.method(,content)
            ConstructInvertedIndexBO elasticSearchBO = ConstructInvertedIndexBO.builder().author("123").body("123").build();
//            List<String> newKeyWords = service.method(elasticSearchBO);
        }else{
            //某个叶子章节
            //es service.method(,content)
        }
    }


}
