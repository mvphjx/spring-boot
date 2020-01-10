package com.svn.stat.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.svn.stat.data.Logentry;
import com.svn.stat.data.SvnLogData;
import com.svn.stat.data.SvnPath;
import com.svn.stat.model.SvnLogModel;
import com.svn.stat.model.SvnLogPK;
import com.svn.stat.repository.SvnLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 日志导入服务
 *      将svn日志导入数据库
 */
@Service
public class ImportService
{
    private final static Logger logger = LoggerFactory.getLogger(ImportService.class);
    @Resource
    SvnLogRepository logRepository;

    /**
     * 将xml导入数据库
     * @param name
     * @param content
     * @throws IOException
     */
    public void processor(String name, String content) throws IOException
    {
        ObjectMapper xmlMapper = new XmlMapper();
        //自动忽略无法对应pojo的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SvnLogData svnLogData = xmlMapper.readValue(content, SvnLogData.class);
        processor(name, svnLogData);
    }

    /**
     * 将xml导入数据库
     * @param name
     * @param file
     * @throws IOException
     */
    public void processor(String name, File file) throws IOException
    {
        ObjectMapper xmlMapper = new XmlMapper();
        //自动忽略无法对应pojo的字段
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SvnLogData svnLogData = xmlMapper.readValue(file, SvnLogData.class);
        processor(name, svnLogData);
    }

    public void processor(String name, SvnLogData svnLogData)
    {
        List<Logentry> logentry = svnLogData.logentry;
        System.out.println(logentry.size());
        for (Logentry logentry1 : logentry)
        {
            for (SvnPath path : logentry1.paths)
            {
                SvnLogPK svnLogPK = new SvnLogPK();
                svnLogPK.name = name;
                svnLogPK.revision = logentry1.revision;
                svnLogPK.path = path.path;
                SvnLogModel svnLogModel = logRepository.getOne(svnLogPK);
                if (svnLogModel.revision == null)
                {
                    svnLogModel = new SvnLogModel();
                    svnLogModel.author = logentry1.author;
                    String date = logentry1.date;
                    date = date.replace("-", "").replace("T", "").replace(":", "").substring(0, 14);
                    svnLogModel.date = date;
                    svnLogModel.msg = logentry1.msg;
                    svnLogModel.revision = logentry1.revision;
                    svnLogModel.kind = path.kind;
                    svnLogModel.path = path.path;
                    svnLogModel.name = name;
                    svnLogModel.textMmods = path.textMmods;
                    svnLogModel.action = path.action;
                    svnLogModel.propMods = path.propMods;
                    logRepository.save(svnLogModel);
                    logger.info("保存成功。" + svnLogModel.revision);
                }
                else
                {
                    logger.info("已存在跳过保存。" + svnLogModel.revision);
                }

            }
        }
    }
}
