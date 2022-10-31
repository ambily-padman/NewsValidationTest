package com.news.utils.data;

public enum SearchEngine {
    GOOGLE,
    BING,
    UNKNOWN;

    public static SearchEngine parse(String searchEngineName) {
        switch (searchEngineName.toLowerCase()) {
            case "google":
                return GOOGLE;
            case "bing":
                return BING;
            default:
                return UNKNOWN;
        }
    }
}
