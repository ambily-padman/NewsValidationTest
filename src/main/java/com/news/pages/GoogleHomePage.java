package com.news.pages;

import com.news.utils.utilities.WebDriverUtils;
import com.news.utils.utilities.WebElementUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Page Model for the Google home page
 */
public class GoogleHomePage extends PageObjectBase {

    GoogleHomePageElements controls;

    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public GoogleHomePage(WebDriver driver) throws InstantiationException {
        super(driver);
        controls = getElements(GoogleHomePageElements.class);
    }

    public void searchTheTerm(String term) {
        controls.inputSearch.sendKeys(term);
        controls.inputSearch.sendKeys(Keys.RETURN);
    }

    public static class GoogleHomePageElements extends Elements {

        @FindBy(name = "q")
        public WebElement inputSearch;

        @FindBy(name = "btnK")
        public WebElement btnGoogleSearch;
    }

}
