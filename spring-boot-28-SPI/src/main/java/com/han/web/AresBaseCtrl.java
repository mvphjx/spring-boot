package com.han.web;

import com.han.service.TestService;
import com.han.service.manage.ServiceMgr;
import com.han.service.support.x001.X001Impl;
import com.han.spi.IAresBaseService;
import com.han.spi.IAresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Http RESTful 标准API
 */
@RestController
@RequestMapping("/api")
public class AresBaseCtrl
{
    @Autowired
    private ServiceMgr mgr;
    @Autowired
    private TestService service;
    @Autowired
    private X001Impl x001;
    @RequestMapping(value = "/do/{key}", method = RequestMethod.GET)
    public String key(@PathVariable String key)
    {
        String systemId = key, dataType = "", subDBName = "";
        IAresBaseService service = mgr.getService(systemId, dataType, subDBName);
        return service.test();
    }
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test()
    {
        return service.test();
    }
}
