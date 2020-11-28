package cn.han.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * 业务封装
 */
@Service
@Transactional
public class CompanyService
{
    @Autowired
    private cn.han.repository.one2many.CompanyRepository companyRepository;
    @Autowired
    private cn.han.repository.one2many.DepartmentRepository departmentRepository;

    public void deleteByComName(String name) {
        companyRepository.deleteByComName(name);
    }

}
