package cn.timebusker.aop;

import cn.timebusker.utils.DateUtil;
import cn.timebusker.utils.HttpClientUtil;
import com.alibaba.fastjson.JSON;
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
public class TPControllerHandlerAop
{

    private final static Logger logger = LoggerFactory.getLogger(ServiceHandlerAop.class);

    /**
     * AOP切面中的同步问题
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 切入点：匹配连接点的断言
     */
    @Pointcut("execution(* cn.timebusker.web..testTP*(..))")
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
            String personId= (String) args[0];
            String fgp=(String) args[1];
            System.out.println(Arrays.toString(args));
            obj = pjp.proceed();
            //改写返回值
            if (obj instanceof HashMap)
            {
                HashMap<String, String> result = (HashMap) obj;
                String status = result.get("status");
                if ("error".equals(status))
                {
                    String tpdata = get(personId,fgp);
                    HashMap<String,String> siyueResult = JSON.parseObject(tpdata,HashMap.class);
                    result.put("status", "ok");
                    result.put("data", siyueResult.get("data"));
                    obj = result;
                }
            }
            System.out.println(this.getClass()+"***MoocAspect around 2.");
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     * 获取思越捺印数据
     */
    public String get(String personId,String fgp)
    {
        String url = "http://127.0.0.1:8080/aop/siyue/tp?personId={{personId}}&fgp={{fgp}}";
        url = url.replace("{{personId}}",personId).replace("{{fgp}}",fgp);
        return HttpClientUtil.get(url);
    }
}
