package com.news.utils.data;

public class GoogleNews {
    String mediaName;
    String newsDescription;
    String newsPostTime;

    public GoogleNews(String mediaName, String newsDescription, String newsPostTime) {
        this.mediaName = mediaName;
        this.newsDescription = newsDescription;
        this.newsPostTime = newsPostTime;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    public String getNewsPostTime() {
        return newsPostTime;
    }

    public void setNewsPostTime(String newsPostTime) {
        this.newsPostTime = newsPostTime;
    }
}
