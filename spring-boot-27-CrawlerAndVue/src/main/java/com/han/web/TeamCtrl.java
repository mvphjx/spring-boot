package com.han.web;

import com.han.jpa.model.Picture;
import com.han.jpa.model.Team;
import com.han.jpa.model.Video;
import com.han.jpa.repository.PicRepository;
import com.han.jpa.repository.TeamRepository;
import com.han.jpa.repository.VideoRepository;
import com.han.service.query.QueryBiz;
import com.han.utils.FileUtil;
import com.han.mybatis.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
public class TeamCtrl
{
    @Resource
    private TeamRepository teamRepository;

    @Autowired
    private QueryBiz queryBiz;

    /**
     * 列表查询
     * @param params
     * @return
     */
    @RequestMapping(value = { "/team/list" }, method = RequestMethod.POST)
    public PageModel list(@RequestBody Map<String, Object> params)
    {
        return queryBiz.queryTeamByMybatis(params);
    }

    /**
     * 查询队伍详情
     * @param uuid
     * @return
     */
    @RequestMapping(value = { "/team/get" }, method = RequestMethod.GET)
    public PageModel get(@RequestParam String uuid)
    {
        PageModel pageModel = new PageModel();
        pageModel.code=20000;
        Team team = teamRepository.findByUuid(uuid);
        for (Video video : team.getVideos())
        {
            video.setTeam(null);
        }
        for (Picture picture : team.getPictures())
        {
            picture.setTeam(null);
        }
        pageModel.data= team;
        return pageModel;
    }
    @Autowired
    PicRepository picRepository;
    /**
     * 获取图片
     * @return
     */
    @RequestMapping(value = { "/picture/{id}" }, method = RequestMethod.GET)
    public void getPicture(@PathVariable Long id, HttpServletResponse response) throws IOException
    {
        Picture picture = picRepository.getOne(id);
        File file = new File(picture.getPath());
        if(file.exists()){
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            OutputStream os = response.getOutputStream();
            FileUtil.getFileOutStream(file, os);
        }
    }
    @Autowired
    VideoRepository videoRepository;
    /**
     * 获取视频
     * @return
     */
    @RequestMapping(value = { "/video/{id}" }, method = RequestMethod.GET)
    public void get(@PathVariable Long id, HttpServletResponse response) throws IOException
    {
        Video video = videoRepository.getOne(id);
        File file = new File(video.getPath());
        if(file.exists()){
            // 配置文件下载
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            OutputStream os = response.getOutputStream();
            FileUtil.getFileOutStream(file, os);
        }
    }



}

