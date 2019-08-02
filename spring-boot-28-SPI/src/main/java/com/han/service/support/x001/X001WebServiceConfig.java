package com.han.service.support.x001;

import com.google.common.collect.Lists;
import com.sun.xml.internal.ws.wsdl.writer.document.Message;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "x001.ws", value = "enabled", havingValue = "true")
@ConfigurationProperties(prefix = "x001")
public class X001WebServiceConfig
{
    private String address;
    private String sig;
    private long connectionTimeout = 10000;
    private long receiveTimeout = 10000;
    private String userRemoteService;

    /**
     * 使用代理类工厂,需要拿到对方的接口
     *
     * @return
     * @throws Exception
     */
    public String userRemoteServiceTest() throws Exception
    {
        try
        {
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            //添加用户名密码拦截器
            jaxWsProxyFactoryBean.getOutInterceptors().add(new LoginInterceptor("root", "admin"));
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(Object.class);
            // 创建一个代理接口实现
            Object cs = jaxWsProxyFactoryBean.create();
            // 数据准备
            String LineId = "1";
            // 调用代理接口的方法调用并返回结果
            Object result = cs.toString();
            System.out.println("==============返回结果:" + result);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 动态调用方式
     *
     * @return
     */
    @Bean
    public String userRemoteService() throws Exception
    {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://127.0.0.1:8080/soap/testWservice?wsdl");
        Object[] objects = client.invoke("list3");//list3方法名 后面是可变参数
        //输出调用结果
        System.out.println(objects[0].getClass());
        System.out.println(objects[0].toString());
        return objects[0].getClass().toString();
    }

    /**
     * 设置连接超时及接收超时
     *
     * @param service
     */
    private void setWSHttpPolicy(Object service)
    {
        Client client = ClientProxy.getClient(service);
        HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy policy = new HTTPClientPolicy();
        policy.setConnectionTimeout(connectionTimeout);
        policy.setReceiveTimeout(receiveTimeout);
        httpConduit.setClient(policy);
    }

    public void setSig(String sig)
    {
        this.sig = sig;
    }

    public void setUserRemoteService(String userRemoteService)
    {
        this.userRemoteService = userRemoteService;
    }

    public void setConnectionTimeout(long connectionTimeout)
    {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReceiveTimeout(long receiveTimeout)
    {
        this.receiveTimeout = receiveTimeout;
    }

}
