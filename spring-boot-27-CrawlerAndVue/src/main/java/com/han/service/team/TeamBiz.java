package com.han.service.team;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.han.jpa.model.Picture;
import com.han.jpa.model.Team;
import com.han.jpa.model.Video;
import com.han.jpa.repository.PicRepository;
import com.han.jpa.repository.TeamRepository;
import com.han.jpa.repository.VideoRepository;
import com.han.service.webcrawler.simple.TeamRepo;
import com.han.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeamBiz
{
    @Resource
    TeamRepository teamRepository;
    @Resource
    PicRepository picRepository;
    @Resource
    VideoRepository videoRepository;
    private final static Logger logger = LoggerFactory.getLogger(TeamBiz.class);
    public static String baseUrl = "http://server.goteaming.com.cn/";
    public static String url = "http://server.goteaming.com.cn/Player_Base/Game/GetTeamDataSource";

    /**
     * 初次 保存全部队伍信息
     *
     * @param teamRepo
     */
    public void init(TeamRepo teamRepo)
    {
        logger.info("队伍总数："+teamRepo.names.size());
        for (int i = 0; i < teamRepo.names.size(); i++)
        {
            Team team = new Team();
            team.setName(teamRepo.names.get(i));
            team.setUuid(teamRepo.uuids.get(i));
            team.setVideos(new ArrayList<>());
            team.setPictures(new ArrayList<>());
            logger.info(">>>>>>Team:"+team.toString());
            Team teamByUuid = teamRepository.findByUuid(team.getUuid());
            if (isFinish(teamByUuid))
            {
                continue;
            }
            else if (teamByUuid != null)
            {
                teamRepository.delete(teamByUuid.getId());
            }
            String teamDataUri = url + "?GTId=" + team.getUuid();
            Map<String, String> header = new HashMap<>();
            header.put("User-Agent",
                    "Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255");
            String jsonStr = HttpClientUtil.get(teamDataUri, header, "utf-8");
            JSONObject jsonObject = (JSONObject) JSONObject.parse(jsonStr);
            //图片
            JSONArray picList = jsonObject.getJSONArray("PicList");
            for (Object o : picList)
            {
                Map<String, String> map = (Map) o;
                Picture picture = new Picture();
                picture.setName(map.get("TaskName"));
                picture.setUrl(baseUrl + map.get("Path"));
                if (!StringUtils.isEmpty(map.get("Path")))
                {
                    logger.info("开始获取：" + picture.getUrl());
                    String filePath = "d:/temp/" + team.getName() + team.getUuid() + "/" + picture.getName() + ".jpg";
                    try
                    {
                        HttpClientUtil.download(picture.getUrl(), filePath);
                        picture.setPath(filePath);
                        logger.info("下载成功路径为：" + filePath);
                    }
                    catch (Exception e)
                    {
                        logger.info("下载失败：" + e.getMessage());
                        picture.setFailCount(1);
                    }
                }
                picture.setTeam(team);
                team.getPictures().add(picture);
            }
            //视频
            JSONArray vedioList = jsonObject.getJSONArray("VedioList");
            for (Object o : vedioList)
            {
                Map<String, String> map = (Map) o;
                Video video = new Video();
                video.setName(map.get("TaskName"));
                video.setUrl(baseUrl + map.get("Path"));
                if (!StringUtils.isEmpty(map.get("Path")))
                {
                    logger.info("开始获取：" + video.getUrl());
                    String filePath = "d:/temp/" + team.getName() + team.getUuid() + "/" + video.getName() + ".mp4";
                    try
                    {
                        HttpClientUtil.download(video.getUrl(), filePath);
                        logger.info("下载成功路径为：" + filePath);
                        video.setPath(filePath);
                    }
                    catch (Exception e)
                    {
                        logger.info("下载失败：" + e.getMessage());
                        video.setFailCount(1);
                    }
                }
                video.setTeam(team);
                team.getVideos().add(video);
            }
            teamRepository.save(team);
        }
    }

    /**
     * 下载单个图片
     */
    public boolean refreshPic(Long id)
    {
        boolean result = true;
        Picture picture = picRepository.findOne(id);
        logger.info("开始获取：" + picture.getUrl());
        Team team = picture.getTeam();
        String filePath = "d:/temp/" + team.getName() + team.getUuid() + "/" + picture.getName() + ".jpg";
        try
        {
            HttpClientUtil.download(baseUrl + picture.getPath(), filePath);
            picture.setPath(filePath);
            logger.info("下载成功路径为：" + filePath);
        }
        catch (Exception e)
        {
            logger.info("下载失败：" + e.getMessage());
            picture.setFailCount(1);
            result = false;
        }
        return result;
    }

    /**
     * 下载单个视频
     */
    public boolean refreshVideo(Long id)
    {
        boolean result = true;
        Video video = videoRepository.findOne(id);
        logger.info("开始获取：" + video.getUrl());
        Team team = video.getTeam();
        String filePath = "d:/temp/" + team.getName() + team.getUuid() + "/" + video.getName() + ".mp4";
        try
        {
            HttpClientUtil.download(baseUrl + video.getPath(), filePath);
            video.setPath(filePath);
            logger.info("下载成功路径为：" + filePath);
        }
        catch (Exception e)
        {
            logger.info("下载失败：" + e.getMessage());
            video.setFailCount(1);
            result = false;
        }
        return result;
    }

    private boolean isFinish(Team team)
    {
        boolean result = true;
        if (team == null)
        {
            return false;
        }
        if (team != null)
        {
            return true;
        }
        if (team.getVideos() == null)
        {
            return false;
        }
        for (Video video : team.getVideos())
        {
            if (video.getPath() == null)
            {
                return false;
            }
        }
        if (team.getPictures() == null)
        {
            return false;
        }
        for (Picture model : team.getPictures())
        {
            if (model.getPath() == null)
            {
                return false;
            }
        }
        return true;
    }

}
