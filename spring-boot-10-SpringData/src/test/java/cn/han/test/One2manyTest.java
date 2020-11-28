package cn.han.test;

import cn.han.App;
import cn.han.model.one2many.Company;
import cn.han.model.one2many.Department;
import cn.han.repository.one2many.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ActiveProfiles("dev")
public class One2manyTest
{
    @Resource
    CompanyRepository companyRepository;
    @Resource
    cn.han.service.CompanyService companyService;



    @Test
    public void saveCompany() throws Exception{
        Company company = new Company();
        company.setComName("公司XXX");
        Department department1 = new Department();
        department1.setDepName("财务部");
        Department department2 = new Department();
        department2.setDepName("研发部");
        List<Department> list = new ArrayList<>();
        list.add(department1);
        list.add(department2);
        company.setDepartment(list);
        Company save = companyRepository.save(company);
        System.out.println("保存公司:getComId****"+save.getComId());
    }


    @Test
    @org.springframework.transaction.annotation.Transactional
    public void deleteCompany() throws Exception{
        companyRepository.deleteByComName("公司XXX");
    }

    @Test
    public void deleteCompanyService() throws Exception{
        companyService.deleteByComName("公司XXX");
    }

    @Test
    public void getCompanyService() throws Exception{
        List<Company> list= companyRepository.findAll();
    }

}
