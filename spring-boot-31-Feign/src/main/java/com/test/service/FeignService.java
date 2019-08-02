package com.test.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feign", url = "https://translate.google.cn/",fallback = FeignServiceImpl.class)
public interface FeignService
{
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String findById();
}
