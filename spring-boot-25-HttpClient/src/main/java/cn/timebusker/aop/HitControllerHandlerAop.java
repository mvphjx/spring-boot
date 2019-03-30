package cn.timebusker.aop;

import cn.timebusker.utils.HttpClientUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 面向切面编程 主要功能：日志记录、性能统计、安全控制、事务处理、异常处理等等
 */
@Component
@Aspect
@Order(15)
public class HitControllerHandlerAop
{

    private final static Logger logger = LoggerFactory.getLogger(ServiceHandlerAop.class);

    /**
     * AOP切面中的同步问题
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 切入点：匹配连接点的断言
     */
    @Pointcut("execution(* cn.timebusker.web..testHIT*(..))")
    public void controller()
    {
    }

    /**
     * 前置通知：在连接点执行前的通知，但不能阻止连接点前的执行（除非它抛出一个异常）
     */
    @Before("controller()")
    public void beforeAdvice()
    {
        logger.info(this.getClass()+"***beforeAdvice");
    }

    @Around(value = "controller()")
    public Object aroundAdvice(ProceedingJoinPoint pjp)
    {
        logger.info(this.getClass()+"***aroundAdvice:");
        Object obj = null;
        try
        {
            // 获取方法参数信息
            Object[] args = pjp.getArgs();
            System.out.println(Arrays.toString(args));
            HashMap<String, String> result = new HashMap<>();
            result.put("status", "ok");
            result.put("data", "假装执行成功");
            obj = result;
            //调用思越接口
            post();
            System.out.println(this.getClass()+"***MoocAspect around 2.");
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 反馈检视结果
     */
    public void post()
    {
        String json = "{\"data\":\"反馈指纹的检视数据\"}";
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type","application/json;charset=utf-8");
        String s = HttpClientUtil.post("http://127.0.0.1:8080/aop/siyue/hit", json, header);
        System.out.println(s);
    }
}
