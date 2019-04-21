package cn.han.repository.one2many;

import cn.han.model.one2many.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface employeeRepository extends JpaRepository<employee, Long>
{

	@Modifying(clearAutomatically = true)
	@Query("update employee set empName =?1 "
			+ "where empId =?2")
	void updateName(@Param(value = "name")String name,
			@Param(value = "id")long id);
}
