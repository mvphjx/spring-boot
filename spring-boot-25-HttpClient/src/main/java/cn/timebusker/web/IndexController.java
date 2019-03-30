package cn.timebusker.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.timebusker.service.CommonService;
import cn.timebusker.service.order.OrderInfoService;
import cn.timebusker.service.user.UserInfoService;
import cn.timebusker.utils.CommonUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
public class IndexController {

	private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Resource
	CommonService service;

	@Resource
	OrderInfoService orderService;

	@Resource
	UserInfoService userService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public Long testAop() {
		logger.info("\n=======================spring aop========================\n");
		int i = 1;
		i = CommonUtil.add(i);
		i = service.add(i);
		logger.info("\n====================处理结果为:" + i + "====================\n");
		String name = "#####";
		name = orderService.addOrderInfo(name);
		name = userService.addUserInfo(name);
		logger.info("\n====================名称为\t" + name + "====================\n");
		return System.currentTimeMillis();
	}

	/**
	 * http://127.0.0.1:8080/aop/tp/123456/1
	 * @param personId
	 * @param fgp
	 * @return
	 */
	@RequestMapping(value = {"/tp/{personId}/{fgp}"}, method = RequestMethod.GET)
	@ResponseBody
	public HashMap testTPAopKey(@PathVariable("personId") String personId,@PathVariable("fgp") String fgp) {
		logger.info("\n=======================指纹系统 捺印数据========================\n");
		logger.info("TP参数为");
		logger.info(personId);
		HashMap<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("status","error");
		hashMap.put("data","指纹系统数据不存在");
		return hashMap;
	}

	@RequestMapping(value = {"/lp/{key}"}, method = RequestMethod.GET)
	@ResponseBody
	public HashMap testLPAopKey(@PathVariable("key") String key) {
		logger.info("\n=======================指纹系统 案件数据========================\n");
		logger.info("LP参数为");
		logger.info(key);
		HashMap<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("status","error");
		return hashMap;
	}

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
