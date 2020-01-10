package com.svn.stat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(SvnLogPK.class)
public class SvnLogModel implements Serializable
{

    @Id
    public String name;
    @Id
    public String revision;
    @Id
    @Column(length = 5000)
    public String path;
    public String author;
    public String date;
    public String msg;
    public String textMmods;
    public String kind;
    public String action;
    public String propMods;

}
