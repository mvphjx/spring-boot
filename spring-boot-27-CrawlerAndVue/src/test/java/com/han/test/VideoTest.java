package com.han.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.CrawlerApp;
import com.han.dao.TableInfoMapper;
import com.han.model.Picture;
import com.han.model.Team;
import com.han.model.Video;
import com.han.repository.TeamRepository;
import com.han.repository.VideoRepository;
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
public class VideoTest
{
    @Resource
    VideoRepository repository;

    /**
     * findOne 可以
     * getOne  报错 why？ todo
     * @throws Exception
     */
    @Test
    public void testVideo() throws Exception{

        Video model = repository.findOne(1L);
        if(model.getTeam()!=null){
            model.getTeam().setPictures(null);
            model.getTeam().setVideos(null);
        }
        System.out.println(model);
    }
}

