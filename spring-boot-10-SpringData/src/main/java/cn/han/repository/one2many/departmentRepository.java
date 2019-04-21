package cn.han.repository.one2many;

import cn.han.model.one2many.Department;
import cn.han.repository.BasicRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface departmentRepository extends BasicRepository<Department, Long>
{

}
