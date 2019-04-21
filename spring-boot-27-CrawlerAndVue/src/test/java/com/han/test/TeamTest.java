package com.han.test;

import cn.han.App;
import cn.han.model.Picture;
import cn.han.model.Team;
import cn.han.model.Video;
import cn.han.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ActiveProfiles("dev")
public class TeamTest
{
    @Resource
    TeamRepository teamRepository;

    /**
     * testCompany
     */
    @Test
    public void testTeam() throws Exception
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
}

