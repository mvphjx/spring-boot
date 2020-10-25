package cn.han.bigdata;

import cn.han.bigdata.model.QueTask;
import cn.han.bigdata.service.ImportService;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportTest
{

    private final static Logger logger = LoggerFactory.getLogger(ImportTest.class);

    @Autowired
    private ImportService service;
    @Test
    public void importTest(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        File file = FileUtil.newFile("bigdata.txt");
        service.importDataSingle(file);
        stopWatch.stop();
        System.out.println("importDataSingle耗時："+stopWatch.getTotalTimeSeconds());

    }

    @Test
    public void importTest2(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        File file = FileUtil.newFile("bigdata.txt");
        service.importData(file,6);
        stopWatch.stop();
        System.out.println("importData耗時："+stopWatch.getTotalTimeSeconds());

    }


}
