package com.fulltext.project.service;
import com.fulltext.project.entity.Company;
import java.util.List;

/**
 * Created by CodeGenerator on 2020/03/06.
 */
public interface CompanyService {
    Company selectCompanyById(Long id);
    List<Company> selectCompanyListByIdList(List<Long> idList);
    int insert(Company entity);
    int update(Company entity);
}