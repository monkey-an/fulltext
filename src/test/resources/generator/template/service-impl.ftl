package ${basePackage}.service.impl;

import ${basePackage}.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by ${author} on ${date}.
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

    @Override
    public ${modelNameUpperCamel} select${modelNameUpperCamel}ById(Long id) {
        return null;
    }

    @Override
    public List<${modelNameUpperCamel}> select${modelNameUpperCamel}ListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(${modelNameUpperCamel} entity) {
        return 0;
    }

    @Override
    public int update(${modelNameUpperCamel} entity) {
        return 0;
    }
}
