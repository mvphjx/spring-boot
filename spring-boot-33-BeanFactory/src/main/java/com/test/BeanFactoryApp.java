package com.test;

import com.test.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Arrays;
import java.util.Map;

/*
 SpringBootServletInitializer
 这个类的作用与在web.xml中配置负责初始化Spring应用上下文的监听器作用类似，
 如果需要打成war部署在tomcat下则需要
 */
@SpringBootApplication
public class BeanFactoryApp
{
    private final static Logger logger = LoggerFactory.getLogger(BeanFactoryApp.class);

    public static void main(String[] args)
    {
        // 启动Sprign Boot
        ApplicationContext ctx = SpringApplication.run(BeanFactoryApp.class, args);
        String[] beanNames = ctx.getBeanNamesForType(IService.class);
        String[] prototypeBeanNames = ctx.getBeanNamesForAnnotation(Scope.class);
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
        {
            logger.info("\n\n");
            logger.info("beanName->" + beanName);
            IService service = (IService) ctx.getBean(beanName);
            Class<? extends IService> classService = service.getClass();
            logger.info("getClass->" + classService.getClass());
            logger.info("getExtSysId->" + service.getExtSysId());
            if (Arrays.asList(prototypeBeanNames).contains(beanName))
            {
                //支持多实例的spi
                Class<ConditionalOnProperty> conditional = ConditionalOnProperty.class;
                ConditionalOnProperty[] annotations = classService.getDeclaredAnnotationsByType(conditional);
                if (annotations != null)
                {
                    String prefix = annotations[0].prefix();
                    //根据参数，创建多个实例
                    String extsysids = ctx.getEnvironment().getProperty(prefix + ".extsysid");
                    if (extsysids != null)
                    {
                        for (String extsysId : extsysids.split(","))
                        {
                            IService service2 = (IService) ctx.getBean(beanName, Integer.valueOf(extsysId));
                            logger.info("getExtSysId2->" + service2.getExtSysId());
                        }
                    }
                }
            }
        }
    }

}
