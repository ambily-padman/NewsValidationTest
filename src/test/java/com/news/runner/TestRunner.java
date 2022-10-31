package com.news.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "com.news.steps"
        , monochrome = true, tags = "@test",
        plugin = {"html:target/cucumber.html", "json:target/cucumber.json",
        })
public class TestRunner {
}
