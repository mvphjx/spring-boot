package com.svn.stat.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.svn.stat.data.Logentry;
import com.svn.stat.data.SvnLogData;

import java.io.File;
import java.util.List;

public class SvnLogfileParser
{
    public static List<Logentry> parser(String fileName) throws Exception
    {
        File file = new File("C:\\PU\\web-common\\svn.log");
        ObjectMapper xmlMapper = new XmlMapper();
        //自动忽略无法对应pojo的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SvnLogData svnLogData = xmlMapper.readValue(file, SvnLogData.class);
        return svnLogData.logentry;
    }
}
