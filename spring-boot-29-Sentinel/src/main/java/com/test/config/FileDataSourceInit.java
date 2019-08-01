package com.test.config;

import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class FileDataSourceInit implements InitFunc
{

    @PostConstruct
    @Override
    public void init() throws Exception
    {
        String degradeRulePath = "D:\\workspace\\github\\spring-boot\\spring-boot-29-Sentinel\\src\\main\\resources\\sentinelconfig.txt";

        ReadableDataSource<String, List<DegradeRule>> ds = new FileRefreshableDataSource<>(degradeRulePath,
                source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>()
                {
                }));
        // 将可读数据源注册至 DegradeRuleManager.
        DegradeRuleManager.register2Property(ds.getProperty());
        WritableDataSource<List<DegradeRule>> wds = new FileWritableDataSource<>(degradeRulePath, this::encodeJson);
        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerDegradeDataSource(wds);
    }

    private <T> String encodeJson(T t)
    {
        return JSON.toJSONString(t);
    }
}
