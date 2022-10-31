package com.news.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BingHomePage extends PageObjectBase {

    BingHomePageElements controls;

    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public BingHomePage(WebDriver driver) throws InstantiationException {
        super(driver);
        controls = getElements(BingHomePageElements.class);
    }

    public void searchTheTerm(String term) {
        controls.inputSearch.sendKeys(term);
        controls.inputSearch.sendKeys(Keys.RETURN);
    }

    public static class BingHomePageElements extends PageObjectBase.Elements {

        @FindBy(name = "q")
        public WebElement inputSearch;
    }
}
