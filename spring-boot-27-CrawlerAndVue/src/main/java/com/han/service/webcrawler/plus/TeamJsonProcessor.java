package com.han.service.webcrawler.plus;

import com.alibaba.fastjson.JSON;
import com.han.contants.Web;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * 爬虫处理 post请求
 */
public class TeamJsonProcessor implements PageProcessor
{
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    /**
     * 获取队伍的 json数据包括图像和视频 描述
     * @param page
     */
    @Override
    public void process(Page page) {
        String paramStr = new String(page.getRequest().getRequestBody().getBody());
        Map param = JSON.parseObject(paramStr, Map.class);
        String groupUuid = (String) param.get("GTId");
        Json json = page.getJson();
        page.putField("data",json);
        page.putField("groupUuid",groupUuid);
        System.out.println(json);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Request request = new Request(Web.TEAMDATA_URL);
        request.setMethod(HttpConstant.Method.POST);
        request.addHeader("User-Agent",Web.USER_AGENT);
        request.setRequestBody(HttpRequestBody.json("{\"GTId\":\"e82fdc56bf4a4d4b968ef587485299a9\"}","utf-8"));
        PageProcessor page = new TeamJsonProcessor();
        Site site = page.getSite();
        site.addHeader("User-Agent",Web.USER_AGENT);
        Spider s = Spider.create(page);
        s.addRequest(request);
        s.run();
    }
}
