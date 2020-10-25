package cn.han.bigdata;

import cn.hutool.cron.CronUtil;
import cn.hutool.cron.Scheduler;

import javax.xml.transform.Source;

public class CronTest
{
    public static void main(String[] args)
    {
        Scheduler scheduler = CronUtil.getScheduler();
        scheduler.setMatchSecond(true);
        //Cron表达式 每一秒执行一次
        scheduler.schedule("0/1 * * * * ?", new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.currentTimeMillis());
            }
        });
        //守护线程启动
        scheduler.start(true);
        try
        {
            Thread.sleep(5*1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        scheduler.stop();
        scheduler.clear();
        //Cron表达式 每一秒执行一次
        scheduler.schedule("0/2 * * * * ?", new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.currentTimeMillis()+"*");
            }
        });
        //守护线程启动
        scheduler.start(true);
        try
        {
            Thread.sleep(5*1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    public void schedule()
    {
        Scheduler scheduler = new Scheduler();
        scheduler.setMatchSecond(true);
        //Cron表达式 每一秒执行一次
        scheduler.schedule("0/1 * * * * ?", new Runnable()
        {
            @Override
            public void run()
            {
                System.out.println(System.currentTimeMillis());
            }
        });
        //守护线程启动
        scheduler.start(true);
        try
        {
            Thread.sleep(5*1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
