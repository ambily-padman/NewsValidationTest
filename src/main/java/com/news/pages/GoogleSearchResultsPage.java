package com.news.pages;

import com.news.utils.data.GoogleNews;
import com.news.utils.utilities.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchResultsPage extends PageObjectBase {

    GoogleSearchResultsPageElements controls;

    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public GoogleSearchResultsPage(WebDriver driver) throws InstantiationException {
        super(driver);
        controls = getElements(GoogleSearchResultsPageElements.class);
    }

    public void clickNewsTab() {
        controls.aNews.click();
    }

    public List<GoogleNews> getAllSearchResults() {
        WebElementUtils.waitForDocumentReadyState(driver);
        List<String> newsList = controls.divResultList.stream().map(x -> x.getText()).collect(Collectors.toList());
        List<GoogleNews> googleNewsDataList = new ArrayList<>();
        int resultCount = 0; //Sometime in google some 'Also in the news' section will come to avoid taking that news into consideration only taking initial 9 results
        for (var news : newsList) {
            resultCount++;
            if (resultCount < 8) {
                String[] items = news.split("\\R");
                googleNewsDataList.add(new GoogleNews(items[0], items[1] + items[2], items[4]));
            }
        }
        return googleNewsDataList;
    }

    public static class GoogleSearchResultsPageElements extends Elements {


        @FindBy(xpath = "//a[contains(text(),'News')]")
        WebElement aNews;

        @FindBy(css = "#rso a")
        List<WebElement> divResultList;
    }
}
