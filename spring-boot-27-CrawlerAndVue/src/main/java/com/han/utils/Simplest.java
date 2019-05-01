package com.han.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;

public class Simplest
{
    private void Get()
    {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        {
            String HostName = "http://www.baidu.com";
            HttpGet httpget = new HttpGet(HostName);
            System.out.println(httpget.getURI());
            //HttpGet httpget = new HttpGet("http://www.lietu.com");
            CloseableHttpResponse response = httpclient.execute(httpget);
            System.out.println("Successful!");
            System.out.println(response.getProtocolVersion());  //Protocol Version
            System.out.println(response.getStatusLine().getStatusCode());   //Status Code
            System.out.println(response.getStatusLine().getReasonPhrase());
            System.out.println(response.getStatusLine().toString());

            //get entity
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                InputStream input = entity.getContent();
                String filename = HostName.substring(HostName.lastIndexOf('/') + 1);
                System.out.println("The filename is: " + filename);
                OutputStream output = new FileOutputStream(filename);

                int tempByte = -1;
                while ((tempByte = input.read()) > 0)
                {
                    output.write(tempByte);
                }

                if (input != null)
                {
                    input.close();
                }

                if (output != null)
                {
                    output.close();
                }
            }
        }
        catch (UnknownHostException e)
        {
            System.out.println("No such a host!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Simplest a = new Simplest();
        a.Get();
        System.out.println("This is a test");
    }
}
