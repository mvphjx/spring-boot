package com.han.service.webcrawler.plus;
/**
 * 根据抽取的领域对象
 * 持久化Team信息
 */

import com.han.jpa.model.Team;
import com.han.jpa.repository.TeamRepository;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TeamPipeline implements Pipeline
{
    @Resource
    TeamRepository service;
    // ResultItems保存了抽取结果，它是一个Map结构，
    // 在page.putField(key,value)中保存的数据，可以通过ResultItems.get(key)获取
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Team> teams  = resultItems.get("teams");
        for(Team team : teams){
            Team model  =service.findByUuid(team.getUuid());
            if(model==null){
                service.save(team);
            }
        }
    }
}
