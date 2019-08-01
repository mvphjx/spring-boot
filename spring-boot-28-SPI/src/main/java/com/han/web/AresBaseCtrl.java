package com.han.web;

import com.han.service.manage.ServiceMgr;
import com.han.spi.IAresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Http RESTful 标准API
 */
@Controller
@RequestMapping("/api")
public class AresBaseCtrl
{
    @Autowired
    private ServiceMgr mgr;

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String search()
    {
        String systemId = "X001Impl", dataType = "", subDBName = "";
        IAresService service = mgr.getService(systemId, dataType, subDBName);
        return service.test();
    }

}
