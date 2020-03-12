package com.fulltext.project.dao;

import com.fulltext.project.entity.Company;
import java.util.List;

public interface CompanyMapper {

    int deleteByPrimaryKey(Long id);


    int insert(Company record);


    Company selectByPrimaryKey(Long id);


    List<Company> selectAll();


    int updateByPrimaryKey(Company record);
}