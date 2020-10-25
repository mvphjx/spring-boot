package cn.han.bigdata.service;

import cn.han.bigdata.model.QueTask;
import cn.hutool.core.io.FileUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.Scheduler;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 多线程 导入数据
 */
@Service
public class ImportService
{
    private final static Logger logger = LoggerFactory.getLogger(ImportService.class);
    public void importDataSingle(File file){
        AtomicLong count = new AtomicLong(0);
        AtomicLong result = new AtomicLong(0);
        startWatch(count,System.currentTimeMillis());
        BufferedReader utf8Reader = FileUtil.getUtf8Reader(file);
        utf8Reader.lines().forEach(s -> {
            QueTask queTask = JSONUtil.toBean(s, QueTask.class);
            result.addAndGet(queTask.getId());
            count.addAndGet(1);
        });
        stopWatch();
        String msg = "count:%d  result:%s ";
        String format = String.format(msg,count.get(),result.get());
        System.out.println(format);

    }

    public void importData(File file,int num){
        AtomicLong count = new AtomicLong(0);
        AtomicLong result = new AtomicLong(0);
        startWatch(count,System.currentTimeMillis());
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        logger.info("executorService:"+num);
        List<CompletableFuture<String>> futures = new ArrayList<>();
        if(num<1){
            num=1;
        }
        for (int i = 0; i < num; i++)
        {
            int lineCount = getLineCount(file);
            int limit=lineCount/num;
            int start=i*limit;
            //存在遗留的数据时，对最后一个分片 进行修正
            int leave=lineCount%num;
            int startLast=(num-1)*limit;
            //对一个或多个 Future 合并操作
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                //模拟业务处理...
                if(leave!=0&&start==startLast){
                    readData(file,startLast,limit+leave,count,result);
                }else {
                    readData(file,start,limit,count,result);
                }
                String msg = "start %d ,limit %d, count:%d,  result:%s ";
                String format = String.format(msg,start,limit,count.get(),result.get());
                return format;
            }, executorService).exceptionally(throwable -> {
                // 异常 处理
                String msg = throwable.toString();
                System.out.println(throwable.toString());
                return msg;
            });
            futures.add(future);
            String msg = String.format("future add start(%d) limit(%d) leave(%d) ",start,limit,leave);
            logger.info(msg);
        }
        logger.info("future run");
        List<String> resultStr =futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        resultStr.forEach(System.out::println);
        executorService.shutdown();
        logger.info("future finish");
        stopWatch();
        String msg = "count:%d  result:%s ";
        String format = String.format(msg,count.get(),result.get());
        System.out.println(format);

    }

    private void readData(File file,int start,int limit,AtomicLong count,AtomicLong result){
        BufferedReader utf8Reader = FileUtil.getUtf8Reader(file);
        Stream<String> stream = utf8Reader.lines().skip(start).limit(limit);
        stream.forEach(s -> {
            QueTask queTask = JSONUtil.toBean(s, QueTask.class);
            result.addAndGet(queTask.getId());
            count.addAndGet(1);
        });
    }

    private void startWatch(AtomicLong count,long startTime){
        Scheduler scheduler = CronUtil.getScheduler();
        //Cron表达式 每5秒执行一次
        scheduler.setMatchSecond(true);
        scheduler.schedule("0/5 * * * * ?", new Runnable()
        {
            @Override
            public void run()
            {
                double totalTimeSeconds = (System.currentTimeMillis()-startTime)/1_000.0;
                String msg = "[Watch]count:%d speed:%.2f";
                String format = String.format(msg,count.get(),count.get()/totalTimeSeconds);
                System.out.println(format);
            }
        });
        scheduler.start();
    }
    private void stopWatch(){
        Scheduler scheduler = CronUtil.getScheduler();
        scheduler.stop();
        scheduler.clear();
    }

    private int getLineCount(File file){
        if(file.exists()){
            BufferedReader utf8Reader = FileUtil.getUtf8Reader(file);
            return (int)utf8Reader.lines().count();
        }else {
            return 0;
        }
    }

}
