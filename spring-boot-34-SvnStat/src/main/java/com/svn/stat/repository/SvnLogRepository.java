package com.svn.stat.repository;

import com.svn.stat.model.SvnLogModel;
import com.svn.stat.model.SvnLogPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SvnLogRepository extends JpaRepository<SvnLogModel, SvnLogPK>
{
}
