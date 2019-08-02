package com.test.web;

import com.test.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestCtrl
{

    @Autowired
    private FeignService service;

    @GetMapping(value = "/feign")
    @ResponseBody
    public String timeOut()
    {
        return service.findById();
    }

}
