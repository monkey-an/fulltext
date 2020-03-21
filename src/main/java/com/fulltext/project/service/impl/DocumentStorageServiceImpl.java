package com.fulltext.project.service.impl;

import com.fulltext.project.constants.ElementTypeEnum;
import com.fulltext.project.dao.DocumentStorageMapper;
import com.fulltext.project.entity.DocumentStorage;
import com.fulltext.project.service.DocumentStorageService;
import com.fulltext.project.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class DocumentStorageServiceImpl implements DocumentStorageService {
    @Resource
    private DocumentStorageMapper documentStorageMapper;

    @Override
    public DocumentStorage selectDocumentStorageById(Long id) {
        return null;
    }

    @Override
    public List<DocumentStorage> selectDocumentStorageListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(DocumentStorage entity) {
        return 0;
    }

    @Override
    public int update(DocumentStorage entity) {
        return 0;
    }

    @Override
    public DocumentStorage getDocumentStorageByDocumentIdAndElementType(Long documentId, Integer elementType) {
        DocumentStorage imageStorage = documentStorageMapper.selectOneByDocumentIdAndElementType(documentId, elementType);
        return imageStorage;
    }

    @Override
    public DocumentStorage getDocumentStorageByDocumentIdAndMenuIdAndElementType(Long documentId, Long menuId, Integer elementType) {
        DocumentStorage imageStorage = documentStorageMapper.selectOneByDocumentIdAndMenuIdAndElementType(documentId, menuId, elementType);
        return imageStorage;
    }

    @Override
    public void downLoadDocument(String documentIdStr, String menuIdStr, String fileType, HttpServletResponse response) throws IOException {
        DocumentStorage documentStorage = null;

        ElementTypeEnum targetType = null;
        if("word".equals(fileType.toLowerCase())){
            targetType = ElementTypeEnum.WORD_DOWN_LOAD_PATH;
        }else{
            targetType = ElementTypeEnum.PDF_DOWN_LOAD_PATH;
        }

        if(StringUtils.isNotEmpty(menuIdStr)) {
            //章节下载
            documentStorage = getDocumentStorageByDocumentIdAndMenuIdAndElementType(Long.parseLong(documentIdStr),Long.parseLong(menuIdStr),targetType.value);
        }else{
            //整本下载
            documentStorage = getDocumentStorageByDocumentIdAndElementType(Long.parseLong(documentIdStr),targetType.value);
        }

        String filePath = documentStorage.getElementPath();
        String fileName = filePath.substring(filePath.lastIndexOf("/")+1);

        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(filePath);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type",type);
        // 设置编码
        String realFileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + realFileName);
        FileUtil.download(filePath, response);
    }
}
