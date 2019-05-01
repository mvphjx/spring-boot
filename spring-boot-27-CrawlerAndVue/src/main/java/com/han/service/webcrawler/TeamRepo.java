package com.han.service.webcrawler;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
@Service
@TargetUrl("http://server.goteaming.com.cn/Player_Base/Game/*")
public class TeamRepo
{
    @ExtractBy("//title")
    public String title;
    @ExtractBy("//div[@class='Team']//text()")
    public List<String> names;
    @ExtractBy("//div[@class='Team']//@gtid")
    public List<String> uuids;

    public static void main(String[] args) {
        Spider spider =OOSpider.create(Site.me(),TeamRepo.class)
                .addUrl("http://server.goteaming.com.cn/Player_Base/Game/ActivityFinish?GameId=9468190e-18bd-4bab-91d5-047f0f4d2289");
        spider.addPipeline(new Pipeline()
        {
            @Override
            public void process(ResultItems resultItems, Task task)
            {
                TeamRepo teamRepo = resultItems.get("TeamRepo");
            }
        });
        spider.run();
    }



}
