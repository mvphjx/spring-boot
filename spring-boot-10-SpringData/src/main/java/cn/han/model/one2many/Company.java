package cn.han.model.one2many;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * 公司表
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_company")
public class Company implements Serializable
{
	private static final long serialVersionUID = 905654767215634L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long comId;

	@Column(nullable = false, length = 32)
	private String comName;

	@OneToMany(cascade= CascadeType.ALL,mappedBy = "company")
	private List<Department> department;

	public long getComId()
	{
		return comId;
	}

	public void setComId(long comId)
	{
		this.comId = comId;
	}

	public String getComName()
	{
		return comName;
	}

	public void setComName(String comName)
	{
		this.comName = comName;
	}

	public List<Department> getDepartment()
	{
		return department;
	}

	public void setDepartment(List<Department> department)
	{
		this.department = department;
	}
}
