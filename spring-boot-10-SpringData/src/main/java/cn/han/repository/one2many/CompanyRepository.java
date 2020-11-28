package cn.han.repository.one2many;

import cn.han.model.one2many.Company;
import cn.han.repository.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends BasicRepository<Company, Long>
{
    public void deleteByComName(String comName);
}
