package com.han.test;

import com.han.CrawlerApp;
import com.han.jpa.model.Picture;
import com.han.jpa.model.Team;
import com.han.jpa.model.Video;
import com.han.jpa.repository.VideoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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

