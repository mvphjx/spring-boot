package com.test;

import com.svn.stat.SvnStatApp;
import com.svn.stat.service.ImportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SvnStatApp.class)
@ActiveProfiles("dev")
public class ImportTest
{
    private final static Logger logger = LoggerFactory.getLogger(ImportTest.class);

    @Resource
    ImportService importService;

    /**
     * 单独保存user
     * success()
     */
    @Test
    public void test() throws Exception
    {
        List<String> names = new ArrayList();
        names.add("web-common");
        names.add("habis-web");
        names.add("habis-ares-j");
        names.add("drj-web");
        names.add("drj");
        names.add("aibbs-web");
        names.add("abis-common-j");
        names.add("ares");
        names.add("habis");
        for (String name : names)
        {
            String fileName = String.format("C:\\PU\\%s\\svn.log",name);
            File file = new File(fileName);
            if(file.exists()){
                importService.processor(name,file);
            }
        }
    }

}
