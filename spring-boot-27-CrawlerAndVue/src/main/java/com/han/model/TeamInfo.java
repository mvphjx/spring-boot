package com.han.model;

import java.io.Serializable;

/**
 * 用户信息
 */
public class TeamInfo implements Serializable {

	private static final long serialVersionUID = -3054513298829319801L;

	private Integer id;

	private String uuid;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
}
