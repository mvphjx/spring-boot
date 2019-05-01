package com.han.service.webcrawler.plus.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * json 的映射对象，降低灵活性，减少解析的复杂度；
 *
 * 对应的灵活解析方式是：
 *    JSONArray picList = jsonObject.getJSONArray("PicList");
 */
public class TeamData
{
    private int status;
    @JsonProperty("PicList")
    private List<ImageCrawler> images;
    @JsonProperty("VedioList")
    private List<VideoCrawler> videos;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ImageCrawler> getImages() {
        return images;
    }

    public void setImages(List<ImageCrawler> images) {
        this.images = images;
    }

    public List<VideoCrawler> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoCrawler> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "TeamData{" +
                "status=" + status +
                ", images=" + images +
                ", videos=" + videos +
                '}';
    }
}
