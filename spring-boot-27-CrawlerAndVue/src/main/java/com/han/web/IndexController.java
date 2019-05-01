package com.han.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class IndexController {

	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);


	@RequestMapping(value = {"/hit/{key}"}, method = RequestMethod.GET)
	@ResponseBody
	public HashMap testHITAopKey(@PathVariable("key") String key) {
		logger.info("\n=======================指纹系统 检视数据========================\n");
		logger.info("HIT参数为");
		logger.info(key);
		HashMap<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("status","error");
		return hashMap;
	}
}
