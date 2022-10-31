package com.news.utils.data;

import java.util.List;

public class BingNews {
    List<String> news;

    public BingNews(List<String> news) {
        this.news = news;
    }

    public List<String> getNews() {
        return news;
    }

    public void setNews(List<String> news) {
        this.news = news;
    }
}
