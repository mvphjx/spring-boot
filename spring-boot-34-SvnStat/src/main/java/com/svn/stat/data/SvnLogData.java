package com.svn.stat.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;
@JacksonXmlRootElement(localName = "log")
public class SvnLogData
{
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "logentry")
    public List<Logentry> logentry;
}
