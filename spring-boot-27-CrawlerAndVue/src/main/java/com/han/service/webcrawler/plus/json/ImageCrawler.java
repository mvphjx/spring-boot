package com.han.service.webcrawler.plus.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageCrawler {
	private static final long serialVersionUID = -6695722256864729383L;

	@JsonProperty("TaskName")
	private String name;
	@JsonProperty("Path")
	private String path;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}
}
