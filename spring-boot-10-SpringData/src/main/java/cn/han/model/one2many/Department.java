package cn.han.model.one2many;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 部门表
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_department")
public class Department
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long depId;

	@Column(nullable = false, length = 32)
	private String depName;

	@ManyToOne(cascade=CascadeType.ALL,targetEntity = Company.class)
	@JoinColumn(name = "com_id")
	private Company company;

	@OneToMany(mappedBy="department")
	private List<employee> employee;

	public long getDepId()
	{
		return depId;
	}

	public void setDepId(long depId)
	{
		this.depId = depId;
	}

	public String getDepName()
	{
		return depName;
	}

	public void setDepName(String depName)
	{
		this.depName = depName;
	}

	public Company getCompany()
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}

	public List<cn.han.model.one2many.employee> getEmployee()
	{
		return employee;
	}

	public void setEmployee(List<cn.han.model.one2many.employee> employee)
	{
		this.employee = employee;
	}
}
