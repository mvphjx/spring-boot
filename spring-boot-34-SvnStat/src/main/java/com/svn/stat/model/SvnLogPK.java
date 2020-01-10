package com.svn.stat.model;

import java.io.Serializable;
import java.util.Objects;

public class SvnLogPK implements Serializable
{
    public String name;
    public String revision;
    public String path;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SvnLogPK svnLogPK = (SvnLogPK) o;
        return Objects.equals(name, svnLogPK.name) && Objects.equals(revision, svnLogPK.revision) && Objects
                .equals(path, svnLogPK.path);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, revision, path);
    }
}

