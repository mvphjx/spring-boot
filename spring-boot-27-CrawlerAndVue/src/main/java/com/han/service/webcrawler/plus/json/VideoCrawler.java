package com.han.service.webcrawler.plus.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoCrawler{
	private static final long serialVersionUID = -6695722256864729383L;
	@JsonProperty("TaskName")
	private String name;
	@JsonProperty("Suffix")
	private String type;
	@JsonProperty("Path")
	private String path;


}
