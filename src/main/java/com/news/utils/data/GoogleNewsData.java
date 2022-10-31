package com.news.utils.data;

import java.util.List;

public class GoogleNewsData {
    List<GoogleNews> googleNewsList;

    public GoogleNewsData(List<GoogleNews> googleNewsList) {
        this.googleNewsList = googleNewsList;
    }

    public List<GoogleNews> getNewsList() {
        return googleNewsList;
    }

    public void setNewsList(List<GoogleNews> googleNewsList) {
        this.googleNewsList = googleNewsList;
    }


}
