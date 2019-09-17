package com.han.web;

import com.han.service.manage.ServiceMgr;
import com.han.spi.IAresBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "/do/{key}", method = RequestMethod.GET)
    public String key(@PathVariable String key) throws Exception
    {
        String systemId = key, dataType = "", subDBName = "";
        IAresBaseService service = mgr.getService(systemId, dataType, subDBName);
        return service.test();
    }
}
