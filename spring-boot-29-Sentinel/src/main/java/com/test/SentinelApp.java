package com.test;

import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SentinelApp
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(SentinelApp.class, args);
        // 启动Sprign Boot
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames)
        {
            System.out.println(beanName);
        }
        initFileRule();
        initFlowRules();
        //initDegradeRule();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    //流量控制 QPS
    private static void initFlowRules()
    {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("timeOut");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rule.setMaxQueueingTimeMs(10);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    //流量控制 线程数
    private static void initFlowThreadRules()
    {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("user");
        rule.setGrade(RuleConstant.FLOW_GRADE_THREAD);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    //熔断降级规则
    private static void initDegradeRule()
    {
        List<DegradeRule> rules = new ArrayList<>();
        //超时降级  平均时间
        DegradeRule rule = new DegradeRule();
        rule.setResource("timeOut");
        rule.setCount(5);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        rule.setTimeWindow(30);
        //异常降级
        DegradeRule exceptionRule = new DegradeRule();
        exceptionRule.setResource("exception");
        exceptionRule.setCount(4);
        exceptionRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        exceptionRule.setTimeWindow(30);
        rules.add(rule);
        rules.add(exceptionRule);
        DegradeRuleManager.loadRules(rules);
    }

    //系统保护规则
    private static void initSystemRule()
    {
        List<SystemRule> rules = new ArrayList<>();
        SystemRule rule = new SystemRule();
        rule.setHighestSystemLoad(10);
        rules.add(rule);
        SystemRuleManager.loadRules(rules);
    }

    /**
     * 从文件加载 规则
     */
    private static Converter<String, List<DegradeRule>> degradeRuleListParser = source -> JSON
            .parseObject(source, new TypeReference<List<DegradeRule>>()
            {
            });

    private static void initFileRule()
    {
        try
        {
            ClassLoader classLoader = SentinelApp.class.getClassLoader();
            String degradeRulePath = URLDecoder.decode(classLoader.getResource("DegradeRule.json").getFile(), "UTF-8");
            // Data source for DegradeRule
            FileRefreshableDataSource<List<DegradeRule>> degradeRuleDataSource = new FileRefreshableDataSource<>(
                    degradeRulePath, degradeRuleListParser);
            DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}
