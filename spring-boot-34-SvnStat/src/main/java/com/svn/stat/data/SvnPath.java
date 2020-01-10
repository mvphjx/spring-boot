package com.svn.stat.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class SvnPath
{
    @JacksonXmlProperty(isAttribute = true, localName = "text-mods")
    public String textMmods;
    @JacksonXmlProperty
    public String kind;
    @JacksonXmlProperty
    public String action;
    @JacksonXmlProperty(isAttribute = true, localName = "prop-mods")
    public String propMods;
    @JacksonXmlText
    public String path;

    @Override
    public String toString()
    {
        return "SvnPath{" + "textMmods='" + textMmods + '\'' + ", kind='" + kind + '\'' + ", action='" + action + '\''
                + ", propMods='" + propMods + '\'' + ", path='" + path + '\'' + '}';
    }
}
