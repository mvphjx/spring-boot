package com.han.service.test;

import com.han.model.Team;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 爬虫实现
 */
public class TeamsCrawler implements PageProcessor
{
    private Site site = Site.me().setDomain("server.goteaming.com.cn");
    public  static  String url =  "http://server.goteaming.com.cn/Player_Base/Game/ActivityFinish";

    @Override
    public void process(Page page)
    {
        List<Team> teams = new ArrayList<>();
        Html html = page.getHtml();
        Selectable selectable =html.$(".Team");
        //<div class="Team active" gtid="a614ae1c-597a-431d-8134-de681cdabca3">队伍3</div>
        for(Selectable node :selectable.nodes()){
            Team team = new Team();
            team.setName(node.$("div","innerHtml").get());
            team.setUuid(node.$("div","gtid").get());
            System.out.println(team);
            teams.add(team);
            //图片
        }
    }
    @Override
    public Site getSite()
    {
        site.addHeader("User-Agent","Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255");
        return site;
    }

    public static void main(String[] args) throws InterruptedException
    {
        url = url+"?GameId=9468190e-18bd-4bab-91d5-047f0f4d2289";
        Spider spider =Spider.create(new TeamsCrawler()).addUrl(url);
        spider.run();
    }
}
