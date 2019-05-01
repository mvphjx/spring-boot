package com.han.web;

import com.han.service.team.TeamBiz;
import com.han.service.webcrawler.simple.TeamRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.HashMap;

/**
 * 爬虫相关接口
 */
@RestController
public class CrawlerCtrl
{
    private final static Logger logger = LoggerFactory.getLogger(CrawlerCtrl.class);
    //00000000-0000-0000-0000-000000000000
    public static final String URL_ALL = "http://server.goteaming.com.cn/Player_Base/Game/ActivityFinish";
    public static final String URL_TEAM = "http://server.goteaming.com.cn/Player_Base/Game/ActivityFinish?GameId=9468190e-18bd-4bab-91d5-047f0f4d2289";

    @Autowired
    private TeamBiz teamBiz;
    @RequestMapping(value = {"/crawler/start"}, method = RequestMethod.GET)
    public HashMap start() throws Exception
    {

        final TeamRepo[] teamRepo = { new TeamRepo() };
        Site me = Site.me();
        me.setTimeOut(10000);
        Spider spider = OOSpider.create(me, TeamRepo.class)
                .addUrl(URL_ALL);
        spider.addPipeline(new Pipeline()
        {
            @Override
            public void process(ResultItems resultItems, Task task)
            {
                teamRepo[0] = resultItems.get("com.han.service.webcrawler.simple.TeamRepo");
            }
        });
        spider.run();
        while (spider.getThreadAlive()>0){
            Thread.sleep(100);
        }
        teamBiz.init(teamRepo[0]);
        HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap.put("status","ok");
        return hashMap;
    }
}
