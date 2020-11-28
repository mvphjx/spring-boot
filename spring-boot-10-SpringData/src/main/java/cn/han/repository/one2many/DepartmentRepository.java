package cn.han.repository.one2many;

import cn.han.model.one2many.Department;
import cn.han.repository.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends BasicRepository<Department, Long>
{
    @org.springframework.data.jpa.repository.Modifying(clearAutomatically = true)
    @org.springframework.data.jpa.repository.Query("delete from Department where  company.comName =?1 ")
    public void deleteByComName(@org.springframework.data.repository.query.Param(value = "comName") String comName);

    public java.util.List<Department> getByComName(String comName);

}
