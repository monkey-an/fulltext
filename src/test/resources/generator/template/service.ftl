package ${basePackage}.service;
import ${basePackage}.entity.${modelNameUpperCamel};
import java.util.List;

/**
 * Created by ${author} on ${date}.
 */
public interface ${modelNameUpperCamel}Service {
    ${modelNameUpperCamel} select${modelNameUpperCamel}ById(Long id);
    List<${modelNameUpperCamel}> select${modelNameUpperCamel}ListByIdList(List<Long> idList);
    int insert(${modelNameUpperCamel} entity);
    int update(${modelNameUpperCamel} entity);
}