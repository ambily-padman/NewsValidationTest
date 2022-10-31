package com.news.steps;

import com.news.pages.*;
import com.news.utils.di.TestContext;
import com.news.utils.utilities.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;


public class GuardianNewsValidationSteps {
    TestContext testContext;
    WebDriver driver;
    GuardianHomePage guardianHomePage;

    public GuardianNewsValidationSteps(TestContext testContext) throws InstantiationException {
        this.testContext = testContext;
        driver = testContext.driverManager.driver;
        guardianHomePage = new GuardianHomePage(driver);
    }

    @Given("I navigate to Guardian home page")
    public void iNavigateToGuardianHomePage() {
        driver.navigate().to(PropertyReader.getInstance().getProperty("guardian.base.url"));
    }

    @And("I get the {int}st headline news listed")
    public void iGetTheStHeadlineNewsListed(int index) {
        testContext.dataStore.add("headline_guardian", guardianHomePage.getHeadline(index));
    }


}
