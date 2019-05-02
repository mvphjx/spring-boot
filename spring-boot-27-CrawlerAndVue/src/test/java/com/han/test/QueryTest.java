package com.han.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.CrawlerApp;
import com.han.jpa.model.Picture;
import com.han.jpa.model.Team;
import com.han.jpa.model.Video;
import com.han.jpa.repository.TeamRepository;
import com.han.mybatis.dao.TableInfoMapper;
import com.han.mybatis.vo.PageModel;
import com.han.service.query.QueryBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrawlerApp.class)
@ActiveProfiles("dev")
public class QueryTest
{
    @Resource
    QueryBiz queryBiz;

    /**
     * testCompany
     */
    @Test
    public void queryTeamByMybatis() throws Exception
    {
        PageModel pageModel = queryBiz.queryTeamByMybatis(null);
        System.out.println(JSON.toJSONString(pageModel));

    }
    @Test
    public void queryTeamByRespository() throws Exception
    {
        PageModel pageModel = queryBiz.queryTeamByRespository(null);
        System.out.println(JSON.toJSONString(pageModel));
    }


}

