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
public class TeamTest
{
    @Resource
    TeamRepository teamRepository;
    @Autowired
    TableInfoMapper teamInfoMapper;

    /**
     * testCompany
     */
    @Test
    public void testSpringDataTeam() throws Exception
    {
        Team team = new Team();
        team.setUuid("Uuid");
        team.setName("1234");
        List<Picture> pictures = new ArrayList<>();
        Picture picture = new Picture();
        picture.setName("pic");
        picture.setTeam(team);
        pictures.add(picture);
        List<Video> videos = new ArrayList<>();
        Video video = new Video();
        video.setName("vi");
        video.setTeam(team);
        videos.add(video);
        team.setPictures(pictures);
        team.setVideos(videos);
        teamRepository.save(team);
    }
    @Test
    public void testMybatisTeam() throws Exception{

        PageHelper.startPage(2, 10);
        List<Map<String, Object>> maps = teamInfoMapper.query(null);
        System.out.println(">>>>>>查询结果数量："+maps.size());
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maps);
        System.out.println( JSON.toJSONString(pageInfo));
    }

    @Test
    public void deleteTeam() throws Exception{

        for (Team team : teamRepository.findAll())
        {
            if(team.getId()>16){
                teamRepository.delete(team.getId());
            }
        }
    }
}

