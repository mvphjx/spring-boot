package com.han.service.webcrawler.plus;

import com.han.contants.Web;
import com.han.jpa.model.Team;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * 队伍爬虫逻辑，获取领域对象 Team.class
 */
public class TeamPageProcessor implements PageProcessor
{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        List<Team> teams = new ArrayList();
        Html h = page.getHtml();
       //<div class="TEAM_URL active" gtid="a614ae1c-597a-431d-8134-de681cdabca3">队伍3</div>
        List<Selectable>  selectables = h.$(".Team").nodes();
        for(Selectable selectable :selectables){
            String uuid = selectable.$("div","gtid").get();
            String name = selectable.xpath("//div/html()").toString();
            Team team = new Team();
            team.setUuid(uuid);
            team.setName(name);
            teams.add(team);
        }
        page.putField("teams",teams);
    }

    @Override
    public Site getSite() {
        return this.site;
    }
    public static void main(String[] args) {
        Spider s = Spider.create(new TeamPageProcessor());
        s.addUrl(Web.TEAM_URL);
        s.run();
    }
}
