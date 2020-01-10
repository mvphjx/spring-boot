package com.test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.svn.stat.data.SvnLogData;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 */
public class FilePathTest
{
    @Test
    public  void load() throws Exception
    {
        String filePath = "src/test/resources/demo.log";
        File file = new File(filePath);
        if (!file.exists())
        {
            System.out.println("文件不存在");
            return;
        }else{
            System.out.println(file.getName());
        }
    }

    @Test
    public  void save() throws Exception
    {
        saveDataToFile("demo.txt", new byte[] { 1, 2 },0,2);
    }

    public static int saveDataToFile(String fileName, byte[] data, int offset, int length)
    {
        try
        {
            FileOutputStream fo = new FileOutputStream(fileName);
            fo.write(data, offset, length);
            fo.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return 1;
    }
}
