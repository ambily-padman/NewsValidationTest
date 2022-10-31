package com.news.utils.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

public class WebElementUtils {

    public static final int DEFAULT_WAIT_TIME_SECONDS = Integer.parseInt(PropertyReader.getInstance().getProperty("selenium.timeout"));
    private static final List<Class<? extends Throwable>> IGNORE_EXCEPTIONS = Arrays.asList(NotFoundException.class, NoSuchElementException.class, StaleElementReferenceException.class);
    private static final int POLL_FREQUENCY_INTERVAL = 100; // in milli-seconds


    /**
     * Clicking the element with the help of action class
     *
     * @param driver
     * @param element
     */
    public static void actionClickTheElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    /**
     * Hover on the element with the help of action class
     *
     * @param driver
     * @param element
     */
    public static void actionHoverTheElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    /**
     * Determines whether the element is present on the page immediately (disregards specified implicit wait on driver instance).
     *
     * @param element The WebElement instance to check
     * @param driver  The WebDriver instance
     * @return True if the element is present, false otherwise
     */
    public static boolean isElementPresent(WebElement element, WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(0, SECONDS);

        try {
            return element.isDisplayed();
        } catch (NoSuchElementException | NullPointerException ex) {
            sleep(200);
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT_TIME_SECONDS));
        }
    }

    /**
     * Checking if the element is displayed
     *
     * @param element
     * @return
     */
    public static boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Wait until the element is visible in DOM
     *
     * @param element
     * @param driver
     * @return
     */
    public static WebElement waitForVisibleElement(WebElement element, WebDriver driver) {
        return waitForVisibleElement(element, driver, Duration.ofSeconds(DEFAULT_WAIT_TIME_SECONDS), Duration.ofMillis(100L));
    }

    /**
     * Wait until the element is clickable in DOM
     *
     * @param element
     * @param driver
     * @return
     */
    public static WebElement waitForElementToBeClickable(WebElement element, WebDriver driver) {
        WebDriverWait wait = createWaitInstance(driver);
        return (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Wait until the element is visible in DOM
     *
     * @param element
     * @param driver
     * @param timeOutInSeconds
     * @param pollIntervalInMs
     * @return
     */
    public static WebElement waitForVisibleElement(WebElement element, WebDriver driver, Duration timeOutInSeconds, Duration pollIntervalInMs) {
        WebDriverWait wait = createWaitInstance(driver, timeOutInSeconds, pollIntervalInMs);
        return (WebElement) wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for the Javascript document ready state to be returned.
     *
     * @param driver The WebDriver instance
     */
    public static void waitForDocumentReadyState(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition =
                d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");
        createWaitInstance(driver).until(pageLoadCondition);
    }

    /**
     * Waits for the specified amount of time by a static Thread.sleep
     *
     * @param millis The number of milliseconds to wait
     */
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {

        }
    }


    /**
     * Scrolls the specified WebElement instance into view.
     *
     * @param element The WebElement source object to scroll into view
     * @param driver  The WebDriver instance
     */
    public static void scrollElementIntoView(WebElement element, WebDriver driver) {
        if (element != null)
            WebDriverUtils.executeScript(driver, "arguments[0].scrollIntoView(true);", element);
    }


    /**
     * Creates an instance of the WebDriverWait class.
     *
     * @param driver The WebDriver instance source object
     * @return An instance of {@link WebDriverWait} bound to the source WebDriver object
     */
    private static WebDriverWait createWaitInstance(WebDriver driver) {
        return createWaitInstance(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME_SECONDS), Duration.ofMillis(POLL_FREQUENCY_INTERVAL));
    }


    /**
     * Creates an instance of the WebDriverWait class.
     *
     * @param driver           The WebDriver instance source object
     * @param timeOutInSeconds The timeout to set (in seconds)
     * @param pollIntervalInMs The interval to poll (in milliseconds)
     * @return An instance of {@link WebDriverWait} bound to the source WebDriver object
     */
    private static WebDriverWait createWaitInstance(WebDriver driver, Duration timeOutInSeconds, Duration pollIntervalInMs) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, pollIntervalInMs);
        wait.ignoreAll(IGNORE_EXCEPTIONS);
        wait.withMessage("The specified element cannot be located, timed out after " + timeOutInSeconds + " seconds");
        return wait;
    }
}
