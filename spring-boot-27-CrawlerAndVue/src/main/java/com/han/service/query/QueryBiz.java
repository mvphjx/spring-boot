package com.han.service.query;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.han.jpa.model.Team;
import com.han.jpa.repository.TeamRepository;
import com.han.mybatis.dao.TableInfoMapper;
import com.han.mybatis.vo.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QueryBiz
{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TableInfoMapper teamInfoMapper;

    /**
     * 基于springdata的分页
     *
     * @param params
     * @return
     */
    public PageModel queryTeamByRespository(Map<String, Object> params)
    {
        int page = 0;
        int size = 20;
        if (params != null)
        {
            page = Integer.valueOf(params.get("page").toString()) - 1;
            size = Integer.valueOf(params.get("limit").toString());
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<Team> teamPage = teamRepository.findAll(pageRequest);
        PageModel listModel = new PageModel();
        listModel.code = 20000;
        HashMap<Object, Object> map = new HashMap<>();
        map.put("total", teamPage.getTotalElements());
        for (Team team : teamPage.getContent())
        {
            team.setVideos(null);
            team.setPictures(null);
        }
        map.put("items", teamPage.getContent());
        listModel.data = map;

        return listModel;
    }

    /**
     * 基于mybatis的分页
     *
     * @param params
     * @return
     */
    public PageModel queryTeamByMybatis(Map<String, Object> params)
    {
        int page = 1;
        int size = 20;
        if (params != null)
        {
            page = Integer.valueOf(params.get("page").toString());
            size = Integer.valueOf(params.get("limit").toString());
        }
        PageHelper.startPage(page, size);
        List<Map<String, Object>> maps = teamInfoMapper.query(params);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(maps);
        PageModel listModel = new PageModel();
        listModel.code = 20000;
        HashMap<Object, Object> map = new HashMap<>();
        listModel.data = new HashMap<>();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getList());
        listModel.data = map;
        return listModel;
    }
}
