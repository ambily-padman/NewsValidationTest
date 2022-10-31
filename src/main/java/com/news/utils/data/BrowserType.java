package com.news.utils.data;

public enum BrowserType {
    UNKNOWN,
    EDGE,
    FIREFOX,
    CHROME,
    SAFARI,
    INTERNET_EXPLORER;

    public static BrowserType parse(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return CHROME;
            case "firefox":
            case "ff":
                return FIREFOX;
            case "edge":
            case "microsoftedge":
                return EDGE;
            case "safari":
            case "safari technology preview":
                return SAFARI;
            case "ie":
            case "iexplore":
            case "internet explorer":
            case "internetexplorer":
                return INTERNET_EXPLORER;
            default:
                return UNKNOWN;
        }
    }
}
