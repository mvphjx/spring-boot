package com.han.entity;

import java.io.Serializable;

/**
 * 用户信息
 */
public class UserPassword implements Serializable {

	private static final long serialVersionUID = -3054513298829319801L;

	private Integer id;

	private Integer userId;

	private String password;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getUserId()
	{
		return userId;
	}

	public void setUserId(Integer userId)
	{
		this.userId = userId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}
