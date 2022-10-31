package com.news.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Model for the Guardian home page
 */
public final class GuardianHomePage extends PageObjectBase {

    GuardianHomePageElements controls;

    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public GuardianHomePage(WebDriver driver) throws InstantiationException {
        super(driver);
        controls = getElements(GuardianHomePageElements.class);
    }

    public String getHeadline(int index) {
        return controls.getNewsItemWithIndex(index).getAttribute("textContent");
    }


    public static class GuardianHomePageElements extends Elements {

        @FindBy(css = ".js-headline-text")
        public List<WebElement> headlineNewsList;

        public WebElement getNewsItemWithIndex(int index) {
            return headlineNewsList.get(index);
        }
    }
}
