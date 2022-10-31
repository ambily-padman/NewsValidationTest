package com.news.pages;

import com.news.utils.data.GoogleNews;
import com.news.utils.utilities.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BingSearchResultsPage extends PageObjectBase {

    BingSearchResultsPageElements controls;

    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public BingSearchResultsPage(WebDriver driver) throws InstantiationException {
        super(driver);
        controls = getElements(BingSearchResultsPageElements.class);
    }

    public void clickNewsTab() {
        WebElementUtils.waitForDocumentReadyState(driver);
        controls.aNews.click();
    }

    public List<String> getAllSearchResults() {
        WebElementUtils.waitForDocumentReadyState(driver);
        List<String> newsList = controls.divResultList.stream().map(x -> x.getText()).collect(Collectors.toList());
        List<String> headLineList = new ArrayList<>();
        int resultCount = 0; //Sometime in google some 'Also in the news' section will come to avoid taking that news into consideration only taking initial 9 results
        for (var news : newsList) {
            resultCount++;
            if (resultCount < 8) {
                String[] items = news.split("\\R");

                headLineList.add(String.join("", items));
            }
        }
        return headLineList;
    }

    public static class BingSearchResultsPageElements extends Elements {


        @FindBy(xpath = "//a[contains(text(),'News')]")
        WebElement aNews;

        @FindBy(css = ".news-card-body")
        List<WebElement> divResultList;
    }
}
