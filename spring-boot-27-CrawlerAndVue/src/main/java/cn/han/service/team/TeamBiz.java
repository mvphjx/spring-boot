package cn.han.service.team;

import cn.han.model.Picture;
import cn.han.model.Team;
import cn.han.model.Video;
import cn.han.repository.TeamRepository;
import cn.han.service.webcrawler.TeamRepo;
import cn.han.utils.HttpClientUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class TeamBiz
{
    @Resource
    TeamRepository teamRepository;
    public  static String baseUrl ="http://server.goteaming.com.cn/";
    public  static  String url =  "http://server.goteaming.com.cn/Player_Base/Game/GetTeamDataSource";
    public Team save(Team team){
        return  teamRepository.save(team);
    }

    public void save(TeamRepo teamRepo){
        for (int i = 0; i < teamRepo.names.size(); i++)
        {
            Team team = new Team();
            team.setName(teamRepo.names.get(i));
            team.setUuid(teamRepo.uuids.get(i));
            team.setVideos(new ArrayList<>());
            team.setPictures(new ArrayList<>());
            String  teamDataUri = url+"?GTId="+teamRepo.uuids.get(i);
            Map<String,String> header = new HashMap<>();
            header.put("User-Agent","Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255");
            String jsonStr = HttpClientUtil.get(teamDataUri, header, "utf-8");
            JSONObject jsonObject  = (JSONObject) JSONObject.parse(jsonStr);
            //图片
            JSONArray picList = jsonObject.getJSONArray("PicList");
            for (Object o : picList)
            {
                Map<String,String> map = (Map) o;
                Picture picture = new Picture();
                picture.setName(map.get("TaskName"));
                picture.setPath(map.get("Path"));
                picture.setTeam(team);
                System.out.println("开始获取："+picture.getPath());
                String filePath = "d:/temp/"+team.getName()+team.getUuid()+"/"+picture.getName()+".jpg";
                //HttpDownload.download(baseUrl+picture.getPath(),filePath);
                System.out.println("下载成功路径为："+filePath);
                picture.setPath(filePath);
                team.getPictures().add(picture);
            }
            //视频
            JSONArray vedioList = jsonObject.getJSONArray("VedioList");
            for (Object o : vedioList)
            {
                Map<String,String> map = (Map) o;
                Video video = new Video();
                video.setName(map.get("TaskName"));
                video.setPath(map.get("Path"));
                video.setTeam(team);
                System.out.println("开始获取："+video.getPath());
                String filePath = "d:/temp/"+team.getName()+team.getUuid()+"/"+video.getName()+".jpg";
                //HttpDownload.download(baseUrl+video.getPath(),filePath);
                System.out.println("下载成功路径为："+filePath);
                video.setPath(filePath);
                team.getVideos().add(video);
            }
            teamRepository.save(team);
        }
    }


}
