package cn.han.bigdata;

import cn.han.bigdata.model.QueTask;
import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CreateTest
{

    private final static Logger logger = LoggerFactory.getLogger(CreateTest.class);

    @Test
    public void logger(){
        String name = logger.getName();
        System.out.println(name);
        System.out.println(logger.isDebugEnabled());
        System.out.println(logger.isInfoEnabled());
        System.out.println(logger.isErrorEnabled());
        logger.info(name);
    }


    @Test
    public void create(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        createAll(1000*10000);
        stopWatch.stop();
        logger.info("总计用时："+stopWatch.getTotalTimeSeconds());
    }

    public void createAll(int count){
        int maxLine = getLineCount();
        List<String> lines = new ArrayList<>();
        for (int i = 1; i <= count; i++)
        {
            QueTask queTask = new QueTask();
            queTask.setId((long) (i+maxLine));
            queTask.setName("T"+System.currentTimeMillis());
            JSONObject jsonObject = JSONUtil.parseObj(queTask);
            String lineStr = jsonObject.toString();
            lines.add(lineStr);
            if(i%(10000)==0){
                //防止內存溢出 1W保存一次
                write(lines);
                lines = new ArrayList<>();
            }
        }
        write(lines);

    }

    public void write(List<String> lines){
        File file = FileUtil.newFile("bigdata.txt");
        FileUtil.appendLines(lines,file,"utf-8");
    }

    @Test
    public void readAll(){
        System.out.println(getLineCount());
        read(100,200);
    }

    public void read(int start,int limit){
        File file = FileUtil.newFile("bigdata.txt");
        BufferedReader utf8Reader = FileUtil.getUtf8Reader(file);
        Stream<String> stream = utf8Reader.lines().skip(start).limit(limit);
        stream.forEach(System.out::println);
    }

    private int getLineCount(){
        File file = FileUtil.newFile("bigdata.txt");
        if(file.exists()){
            BufferedReader utf8Reader = FileUtil.getUtf8Reader(file);
            return (int)utf8Reader.lines().count();
        }else {
            return 0;
        }


    }

    public static void main(String[] args)
    {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
        integerStream.skip(2).limit(2).forEach(integer -> System.out.println("integer = " +  integer));
    }


}
