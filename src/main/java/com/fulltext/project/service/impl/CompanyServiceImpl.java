package com.fulltext.project.service.impl;

import com.fulltext.project.dao.CompanyMapper;
import com.fulltext.project.entity.Company;
import com.fulltext.project.service.CompanyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2020/03/06.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyMapper companyMapper;

    @Override
    public Company selectCompanyById(Long id) {
        return null;
    }

    @Override
    public List<Company> selectCompanyListByIdList(List<Long> idList) {
        return null;
    }

    @Override
    public int insert(Company entity) {
        return 0;
    }

    @Override
    public int update(Company entity) {
        return 0;
    }
}
