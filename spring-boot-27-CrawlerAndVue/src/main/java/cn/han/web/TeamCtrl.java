package cn.han.web;

import cn.han.model.Picture;
import cn.han.model.Team;
import cn.han.model.Video;
import cn.han.repository.TeamPagingAndSortRespository;
import cn.han.repository.TeamRepository;
import cn.han.vo.ListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeamCtrl
{

    @Autowired
    private TeamPagingAndSortRespository pagingAndSortRespository;
    @Resource
    TeamRepository teamRepository;

    @RequestMapping(value = { "/team/list" }, method = RequestMethod.POST)
    public ListModel list(@RequestBody Map<String, Object> params)
    {
        int page = Integer.valueOf(params.get("page").toString()) - 1;
        int size = Integer.valueOf(params.get("limit").toString());
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Team> teamPage = pagingAndSortRespository.findAll(pageRequest);
        ListModel listModel = new ListModel();
        listModel.code = 20000;
        listModel.data = new HashMap<>();
        listModel.data.put("total", teamPage.getTotalElements());
        for (Team team : teamPage.getContent())
        {
            team.setVideos(null);
            team.setPictures(null);
        }
        listModel.data.put("items", teamPage.getContent());
        return listModel;
    }

    @RequestMapping(value = { "/team/get" }, method = RequestMethod.GET)
    public Map list(@RequestParam String uuid)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "ok");
        map.put("code", 20000);
        Team team = teamRepository.findByUuid(uuid);
        for (Video video : team.getVideos())
        {
            video.setTeam(null);
        }
        for (Picture picture : team.getPictures())
        {
            picture.setTeam(null);
        }
        map.put("data", team);
        return map;
    }
}

