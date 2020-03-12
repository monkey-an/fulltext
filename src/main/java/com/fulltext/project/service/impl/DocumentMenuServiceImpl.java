package com.fulltext.project.service.impl;

import com.fulltext.project.bo.DocumentMenuNode;
import com.fulltext.project.dao.DocumentMenuMapper;
import com.fulltext.project.entity.DocumentMenu;
import com.fulltext.project.service.DocumentMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class DocumentMenuServiceImpl implements DocumentMenuService {
    @Resource
    private DocumentMenuMapper documentMenuMapper;

    @Override
    public DocumentMenu selectDocumentMenuById(Long id) {
        return documentMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DocumentMenu> selectDocumentMenuListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(DocumentMenu entity) {
        return 0;
    }

    @Override
    public int update(DocumentMenu entity) {
        return 0;
    }

    @Override
    public List<DocumentMenuNode> getDocumentMenuListByDocumentId(Long documentId) {
        List<DocumentMenu> originList = documentMenuMapper.selectByDocumentId(documentId);
        List<DocumentMenuNode> resultList = new ArrayList<>();
        resultList.addAll(originList.stream()
                .filter(origin -> origin.getParentMenuId() == null)
                .map(rootMenu -> DocumentMenuNode.builder().node(rootMenu).build())
                .collect(Collectors.toList()));

            for (DocumentMenuNode rootNode : resultList) {
                loadMenuNode(rootNode,originList);
            }

        return resultList;
    }

    private void loadMenuNode(DocumentMenuNode rootNode, List<DocumentMenu> allMenu) {
        rootNode.setSubNodeList(new ArrayList<>());
        List<DocumentMenu> tempList = allMenu.stream()
                .filter(menu -> rootNode.getNode().getId().equals(menu.getParentMenuId()))
                .collect(Collectors.toList());

        if (tempList == null) {
            return;
        } else {
            tempList.forEach(temp -> {
                DocumentMenuNode tempNode = DocumentMenuNode.builder().node(temp).build();
                rootNode.getSubNodeList().add(tempNode);
                loadMenuNode(tempNode,allMenu);
            });
        }
    }
}
