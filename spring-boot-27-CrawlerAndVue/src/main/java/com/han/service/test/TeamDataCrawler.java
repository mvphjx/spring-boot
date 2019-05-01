package com.han.service.test;

import com.han.model.Picture;
import com.han.model.Video;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Json;
import us.codecraft.webmagic.selector.Selectable;

/**
 * 爬虫实现
 */
public class TeamDataCrawler implements PageProcessor
{
    private Site site = Site.me().setDomain("server.goteaming.com.cn");
    public  static  String url = "http://server.goteaming.com.cn/Player_Base/Game/GetTeamDataSource";
    @Override
    public void process(Page page)
    {
        System.out.println("process");
        Json json = page.getJson();
        //{"status":1,"VedioList":[{"Path":"/Content/Files/Answer/d166efd2-7605-4f45-8f99-0f802201f331.mp4","TaskName":"我心永恒","Suffix":"mp4"}],"PicList":[]}
        Selectable videos = json.jsonPath("VedioList");
        for(Selectable node :videos.nodes()){
            Video video = new Video();
            Json videoJson = new Json(node.all());
            video.setName(videoJson.jsonPath("TaskName").get());
            video.setPath(videoJson.jsonPath("Path").get());
        }
        Selectable pictures = json.jsonPath("PicList");
        for(Selectable node :pictures.nodes()){
            Json pictureJson = new Json(node.all());
            Picture picture = new Picture();
            picture.setName(pictureJson.jsonPath("TaskName").get());
            picture.setPath(pictureJson.jsonPath("Path").get());
        }
    }
    @Override
    public Site getSite()
    {
        site.setUserAgent("Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255");
        return site;
    }

    public static void main(String[] args) throws InterruptedException
    {

        //图片
        url = url+"?GTId=beff1f96-328e-4c47-a105-19221997f19d";
//        Request request = new Request(url);
//        request.setMethod(HttpConstant.Method.POST);
//        request.addHeader("User-Agent","Mozilla/5.0 (Linux; U; Android 2.3.6; zh-cn; GT-S5660 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1 MicroMessenger/4.5.255");
//        request.setRequestBody(HttpRequestBody.json("{'GTId':}","utf-8"));
        Spider spider =Spider.create(new TeamDataCrawler()).addUrl(url);
        //spider.addPipeline()
        spider.run();
        while (spider.getThreadAlive()>0){
            Thread.sleep(100L);
        }
        System.out.println(spider.getThreadAlive());

    }
}
