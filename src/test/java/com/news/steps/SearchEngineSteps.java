package com.news.steps;

import com.news.pages.*;
import com.news.utils.data.BingNews;
import com.news.utils.data.GoogleNews;
import com.news.utils.data.GoogleNewsData;
import com.news.utils.data.SearchEngine;
import com.news.utils.di.TestContext;
import com.news.utils.utilities.APIUtils;
import com.news.utils.utilities.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class SearchEngineSteps {
    TestContext testContext;
    WebDriver driver;
    GoogleHomePage googleHomePage;
    GoogleSearchResultsPage googleSearchResultsPage;
    GoogleCookieContainer googleCookieContainer;
    BingCookieContainer bingCookieContainer;
    BingHomePage bingHomePage;
    BingSearchResultsPage bingSearchResultsPage;
    APIUtils apiUtils;

    public SearchEngineSteps(TestContext testContext) throws InstantiationException {
        this.testContext = testContext;
        driver = testContext.driverManager.driver;
        googleHomePage = new GoogleHomePage(driver);
        googleSearchResultsPage = new GoogleSearchResultsPage(driver);
        googleCookieContainer = new GoogleCookieContainer(driver);
        bingCookieContainer = new BingCookieContainer(driver);
        bingHomePage = new BingHomePage(driver);
        bingSearchResultsPage = new BingSearchResultsPage(driver);
        apiUtils = new APIUtils();
    }

    @And("I navigated to {string} home page")
    public void iNavigatedToXHomePage(String searchEngine) {
        navigateToSearchEngine(SearchEngine.parse(searchEngine));
    }

    @When("I search the headline news in {string}")
    public void iSearchTheHeadlineNewsInGoogle(String searchEngine) {
        searchHeadLineInSearchEngine(SearchEngine.parse(searchEngine));
    }

    @And("I select the news tab in {string} search result page")
    public void iSelectTheNewsTabInSearchResultPage(String searchEngine) {
        switchToNewsTabInSearchEngine(SearchEngine.parse(searchEngine));

    }

    @And("Get all the search {string} results")
    public void getAllTheSearchResults(String searchEngine) {
        switch (SearchEngine.parse(searchEngine)) {
            case GOOGLE:
                List<GoogleNews> list = googleSearchResultsPage.getAllSearchResults();
                testContext.dataStore.add("google_news", new GoogleNewsData(list));
                break;
            case BING:
                List<String> bingList = bingSearchResultsPage.getAllSearchResults();
                testContext.dataStore.add("bing_news", new BingNews(bingList));
                break;
            default:
                throw new IllegalArgumentException("Not a valid search engine option");

        }

    }

    @Then("I can see the news is a valid news as of {} results")
    public void iCanSeeTheNewsIsAValidNews(String searchEngine) throws InterruptedException {
        switch (SearchEngine.parse(searchEngine)) {
            case GOOGLE:
                assertTrue("Atleast 2 other sources are not showing similar news, So the news seems fake!",
                        apiUtils.isTheNewsSemanticallyMatchesWithGoogleResults(
                                testContext.dataStore.get("headline_guardian", String.class),
                                testContext.dataStore.get("google_news", GoogleNewsData.class)));
                break;
            case BING:
                assertTrue("Atleast 2 other sources are not showing similar news, So the news seems fake!",
                        apiUtils.isTheNewsSemanticallyMatchesWithBingResults(
                                testContext.dataStore.get("headline_guardian", String.class),
                                testContext.dataStore.get("bing_news", BingNews.class)));
                break;
            default:
                throw new IllegalArgumentException("Not a valid search engine option");

        }
    }

    private void navigateToSearchEngine(SearchEngine searchEngine) {
        switch (searchEngine) {
            case GOOGLE:
                driver.navigate().to(PropertyReader.getInstance().getProperty("google.base.url"));
                googleCookieContainer.acceptAllCookies();
                break;
            case BING:
                driver.navigate().to(PropertyReader.getInstance().getProperty("bing.base.url"));
                bingCookieContainer.acceptAllCookies();
                break;
            default:
                throw new IllegalArgumentException("Not a valid search engine option");
        }
    }

    private void searchHeadLineInSearchEngine(SearchEngine searchEngine) {
        switch (searchEngine) {
            case GOOGLE:
                googleHomePage.searchTheTerm(testContext.dataStore.get("headline_guardian", String.class));
                break;
            case BING:
                bingHomePage.searchTheTerm(testContext.dataStore.get("headline_guardian", String.class));
                break;
            default:
                throw new IllegalArgumentException("Not a valid search engine option");
        }
    }

    private void switchToNewsTabInSearchEngine(SearchEngine searchEngine) {
        switch (searchEngine) {
            case GOOGLE:
                googleSearchResultsPage.clickNewsTab();
                break;
            case BING:
                bingSearchResultsPage.clickNewsTab();
                break;
            default:
                throw new IllegalArgumentException("Not a valid search engine option");
        }
    }
}
