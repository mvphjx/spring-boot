package com.svn.stat.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Logentry
{
    @JacksonXmlProperty(isAttribute = true)
    public String revision;
    public String author;
    public String date;
    public String msg;
    @JacksonXmlElementWrapper(localName = "paths")
    @JacksonXmlProperty(localName = "path")
    public List<SvnPath> paths;


}
