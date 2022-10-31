package com.news.utils.di;


import com.news.pages.PageObjectBase;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

/**
 * Instance of this class will be injecting to the step deefinition classes
 */
public class TestContext {

    public WebDriver driver;
    public PageObjectBase pageObjectBase;
    public DriverManager driverManager;
    public DataStore dataStore;
    public Scenario scenario;


    public TestContext() {
        driverManager = new DriverManager();
        pageObjectBase = new PageObjectBase(driverManager.createDriver());
        dataStore = new DataStore();
    }

}
