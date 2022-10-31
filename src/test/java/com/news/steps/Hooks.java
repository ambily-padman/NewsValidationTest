package com.news.steps;


import com.news.utils.di.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.io.IOException;

public class Hooks {
    TestContext testContext;

    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    @Before("@test")
    public void before(Scenario scenario) {
        this.testContext.scenario = scenario;
    }


    @After("@test")
    public void AfterScenario() throws IOException {
        testContext.driverManager.driver.quit();
    }
}
