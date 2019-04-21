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
@SpringBootTest(classes= App.class)
@ActiveProfiles("dev")
public class One2manyTest
{
    @Resource
    CompanyRepository companyRepository;


    /**
     * testCompany
     */
    @Test
    public void testCompany() throws Exception
    {
        Company company = new Company();
        company.setComName("first");
        List<Department> list = new ArrayList<>();
        Department department1 = new Department();
        department1.setDepName("Department1");
        //department1.setCompany(company);
        list.add(department1);
        Department department2 = new Department();
        department2.setDepName("Department2");
        //department2.setCompany(company);
        list.add(department2);
        company.setDepartment(list);
        Company save = companyRepository.save(company);
        System.out.println("保存公司:getComId****"+save.getComId());

    }
}
