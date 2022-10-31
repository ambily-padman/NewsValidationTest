package com.news.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCookieContainer extends PageObjectBase {

    GoogleCookieContainerElements controls;

    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public GoogleCookieContainer(WebDriver driver) throws InstantiationException {
        super(driver);
        controls = getElements(GoogleCookieContainerElements.class);
    }


    public void acceptAllCookies() {
        controls.btnAcceptAll.click();
    }

    public static class GoogleCookieContainerElements extends Elements {
        @FindBy(id = "L2AGLb")
        WebElement btnAcceptAll;
    }


}
