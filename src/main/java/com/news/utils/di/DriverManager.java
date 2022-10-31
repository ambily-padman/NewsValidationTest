package com.news.utils.di;


import com.news.utils.data.BrowserType;
import com.news.utils.data.PlatformType;
import com.news.utils.utilities.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverManager {
    public WebDriver driver;

    public WebDriver createDriver() {
        configureWebDriverRuntimeProperties();
        driver = getDriver(BrowserType.parse(PropertyReader.getInstance().getProperty("browser.type")));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return driver;
    }

    private static void configureWebDriverRuntimeProperties() {
        // configure driver executables
        PlatformType os = PlatformType.fromDetectedOperatingSystem();

        String chromedriverExecutable = (os == PlatformType.WINDOWS) ? "chromedriver.exe" : "chromedriver";
        String geckodriverExecutable = (os == PlatformType.WINDOWS) ? "geckodriver.exe" : "geckodriver";
        String runtimeDirectory = PropertyReader.getInstance().getProperty("driver.location");

        // ensure the end directory character is specified
        if (!runtimeDirectory.endsWith("/") || !runtimeDirectory.endsWith("\\")) {
            runtimeDirectory = runtimeDirectory + ((os == PlatformType.WINDOWS) ? "\\" : "/");
        }
        System.setProperty("webdriver.chrome.driver", runtimeDirectory + chromedriverExecutable);
        System.setProperty("webdriver.gecko.driver", runtimeDirectory + geckodriverExecutable);

    }

    private static WebDriver getDriver(BrowserType browser) {

        WebDriver driver;

        switch (browser) {
            case EDGE:
                driver = new EdgeDriver();
                break;
            case INTERNET_EXPLORER:
                driver = new InternetExplorerDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case SAFARI:
                driver = new SafariDriver();
                break;
            case UNKNOWN:
            default:
                throw new IllegalArgumentException(String.format(" %s is not a  invalid browser", browser));

        }

        return driver;
    }

}
