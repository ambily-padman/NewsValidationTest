package com.news.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BingCookieContainer extends PageObjectBase {

    BingCookieContainerElements controls;

    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public BingCookieContainer(WebDriver driver) throws InstantiationException {
        super(driver);
        controls = getElements(BingCookieContainerElements.class);
    }


    public void acceptAllCookies() {
        controls.btnAcceptAll.click();
    }

    public static class BingCookieContainerElements extends Elements {
        @FindBy(id = "bnp_btn_accept")
        WebElement btnAcceptAll;
    }
}
