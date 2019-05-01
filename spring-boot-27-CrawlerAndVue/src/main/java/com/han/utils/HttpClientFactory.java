package com.han.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

public final class HttpClientFactory {

    private HttpClientConnectionManager simpleHttpConnectionManager;
    // 这里就直接默认固定了,因为以下三个参数在新建的method中仍然可以重新配置并被覆盖.
    static final int connectionRequestTimeout = 5000;// ms毫秒,从池中获取链接超时时间
    static final int connectTimeout = 5000;// ms毫秒,建立链接超时时间
    static final int socketTimeout = 30000;// ms毫秒,读取超时时间
    public void setSimpleHttpConnectionManager(HttpClientConnectionManager simpleHttpConnectionManager) {
        this.simpleHttpConnectionManager = simpleHttpConnectionManager;
    }

    public CloseableHttpClient getHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout).setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        ConnectionConfig config = ConnectionConfig.custom().setCharset(Charset.forName("UTF-8")).build();
        HttpClientBuilder httpClientBuilder = HttpClients.custom().setConnectionManager(simpleHttpConnectionManager);
        return httpClientBuilder.setDefaultRequestConfig(requestConfig).build();
    }
}
