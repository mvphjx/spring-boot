package com.han.utils;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * HttpClientUtil 工具类型封装了http协议 通过 get post交互
 */
public final class HttpClientUtil
{

    private static HttpClientFactory simpleHttpClientFactory;

    private HttpClientUtil()
    {
    }

    private static final String DEFAULT_CHARSET = "UTF-8";

    static
    {
        simpleHttpClientFactory = new HttpClientFactory();
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(500);
        simpleHttpClientFactory.setSimpleHttpConnectionManager(manager);
    }

    public static String get(String requestURI, Map<String, String> header, String charset)
    {
        HttpClient client = simpleHttpClientFactory.getHttpClient();
        HttpGet request = new HttpGet(requestURI);
        request.setConfig(RequestConfig.DEFAULT);
        try
        {
            if (Objects.nonNull(header) && header.size() > 0)
            {
                header.entrySet().forEach(entry -> request.addHeader(entry.getKey(), entry.getValue()));
            }
            HttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity(), charset);
        }
        catch (Exception error)
        {
            throw new RuntimeException(error);
        }
    }

    public static String get(String requestURI, Map<String, String> header)
    {
        return HttpClientUtil.get(requestURI, header, DEFAULT_CHARSET);
    }

    public static String get(String requestURI, String charset)
    {
        HttpClient client = simpleHttpClientFactory.getHttpClient();
        HttpGet request = new HttpGet(requestURI);
        request.setConfig(RequestConfig.DEFAULT);
        try
        {
            HttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity(), charset);
        }
        catch (Exception error)
        {
            throw new RuntimeException(error);
        }
    }

    public static String get(String requestURI)
    {
        return HttpClientUtil.get(requestURI, DEFAULT_CHARSET);
    }

    public static String post(String requestURI, Map<String, Object> params, Map<String, String> header, String charset)
    {
        HttpClient client = simpleHttpClientFactory.getHttpClient();
        HttpPost request = new HttpPost(requestURI);
        request.setConfig(RequestConfig.DEFAULT);
        List<NameValuePair> pairs = new ArrayList<>();
        for (String key : params.keySet())
        {
            String val = params.get(key).toString();
            pairs.add(new BasicNameValuePair(key, val));
        }

        try
        {
            if (Objects.nonNull(header) && header.size() > 0)
            {
                header.entrySet().forEach(entry -> request.addHeader(entry.getKey(), entry.getValue()));
            }
            request.setEntity(new UrlEncodedFormEntity(pairs, charset));
            HttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }
        catch (Exception error)
        {
            throw new RuntimeException(error);
        }
    }

    public static String post(String requestURI, Map<String, Object> params, Map<String, String> header)
    {
        return HttpClientUtil.post(requestURI, params, header, DEFAULT_CHARSET);
    }

    public static String post(String requestURI, String params, Map<String, String> header, String charset)
    {
        HttpClient client = simpleHttpClientFactory.getHttpClient();
        HttpPost request = new HttpPost(requestURI);
        request.setConfig(RequestConfig.DEFAULT);
        try
        {
            if (Objects.nonNull(header) && header.size() > 0)
            {
                header.entrySet().forEach(entry -> request.addHeader(entry.getKey(), entry.getValue()));
            }
            request.setEntity(new StringEntity(params, charset));
            HttpResponse response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }
        catch (Exception error)
        {
            throw new RuntimeException(error);
        }
    }

    public static String post(String requestURI, String params, Map<String, String> header)
    {
        return HttpClientUtil.post(requestURI, params, header, DEFAULT_CHARSET);
    }

    public static final int cache = 10 * 1024;
    public static final boolean isWindows;
    public static final String splash;
    public static final String root;

    static
    {
        if (System.getProperty("os.name") != null && System.getProperty("os.name").toLowerCase().contains("windows"))
        {
            isWindows = true;
            splash = "\\";
            root = "D:";
        }
        else
        {
            isWindows = false;
            splash = "/";
            root = "/search";
        }
    }

    /**
     * 根据url下载文件，文件名从response header头中获取
     *
     * @param url
     * @return
     */
    public static String download(String url) throws Exception
    {
        return download(url, null);
    }

    /**
     * 根据url下载文件，保存到filepath中
     *
     * @param url
     * @param filepath
     * @return
     */
    public static String download(String url, String filepath) throws Exception
    {

        HttpClient client = simpleHttpClientFactory.getHttpClient();
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = client.execute(httpget);
        if (response.getStatusLine().getStatusCode() != 200)
        {
            httpget.releaseConnection();
            throw new RuntimeException("404,文件不存在");
        }
        HttpEntity entity = response.getEntity();
        InputStream is = entity.getContent();
        if (filepath == null) filepath = getFilePath(response);
        File file = new File(filepath);
        file.getParentFile().mkdirs();
        FileOutputStream fileout = new FileOutputStream(file);
        /**
         * 根据实际运行效果 设置缓冲区大小
         */
        byte[] buffer = new byte[cache];
        int ch = 0;
        while ((ch = is.read(buffer)) != -1)
        {
            fileout.write(buffer, 0, ch);
        }
        is.close();
        fileout.flush();
        fileout.close();
        return filepath;
    }

    /**
     * 获取response要下载的文件的默认路径
     *
     * @param response
     * @return
     */
    public static String getFilePath(HttpResponse response)
    {
        String filepath = root + splash;
        String filename = getFileName(response);

        if (filename != null)
        {
            filepath += filename;
        }
        else
        {
            filepath += getRandomFileName();
        }
        return filepath;
    }

    /**
     * 获取response header中Content-Disposition中的filename值
     *
     * @param response
     * @return
     */
    public static String getFileName(HttpResponse response)
    {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null)
        {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1)
            {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null)
                {
                    try
                    {
                        //filename = new String(param.getValue().toString().getBytes(), "utf-8");
                        //filename=URLDecoder.decode(param.getValue(),"utf-8");
                        filename = param.getValue();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filename;
    }

    /**
     * 获取随机文件名
     *
     * @return
     */
    public static String getRandomFileName()
    {
        return String.valueOf(System.currentTimeMillis());
    }

    public static void outHeaders(HttpResponse response)
    {
        Header[] headers = response.getAllHeaders();
        for (int i = 0; i < headers.length; i++)
        {
            System.out.println(headers[i]);
        }
    }
}
