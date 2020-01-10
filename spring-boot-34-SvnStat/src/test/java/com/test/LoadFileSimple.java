package com.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.svn.stat.data.SvnLogData;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 */
public class LoadFileSimple
{
    public static void main(String[] args) throws Exception
    {
        String filePath = "src/test/resources/demo.log";
        File file = new File(filePath);
        if (!file.exists())
        {
            //静态方法读取不到？？？  TODO
            System.out.println("文件不存在");
            return;
        }
    }

    @Test
    public void load() throws Exception
    {
        String filePath = "src/test/resources/demo.log";
        File file = new File(filePath);
        if (!file.exists())
        {
            System.out.println("文件不存在");
            return;
        }
        //读取程序下/程序下 文件
        ObjectMapper xmlMapper = new XmlMapper();
        //自动忽略无法对应pojo的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SvnLogData svnLogData = xmlMapper.readValue(file, SvnLogData.class);
        System.out.println(svnLogData.logentry.size());
        System.out.println(svnLogData.logentry.get(0).paths);
    }
}
