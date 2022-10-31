package com.news.pages;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;


/**
 * Base class for all page object definitions
 */
public class PageObjectBase {

    protected WebDriver driver;

    protected Elements elements;
    private Class<? extends Elements> elementsClass;


    /**
     * Base constructor for a page object
     *
     * @param driver The WebDriver instance to use
     */
    public PageObjectBase(WebDriver driver) {

        this.driver = driver;
        this.elementsClass = null;
    }


    /**
     * Gets the instance of the Elements class that contains the WebElement controls for the model
     *
     * @param c   The class of the Elements type to return
     * @param <T> The type to return (must extend PageObjectBase$Elements)
     * @return The instance of the Elements class as an instance of the specified type
     * @throws InstantiationException If the Elements class cannot be instantiated
     */
    @SuppressWarnings("unchecked")
    protected <T extends Elements> T getElements(Class<T> c)
            throws InstantiationException {

        try {
            if ((elements == null) || (!c.isInstance(elements))) {
                // create and init the Elements instance
                this.elements = c.getDeclaredConstructor().newInstance();
                this.elements.initialise(this.driver);
            }
        } catch (Exception ex) {
            throw new InstantiationException("The instance of the Elements class cannot be created from the specified inner class name");
        }

        if (!c.isInstance(elements)) {
            throw new InstantiationException("The 'Elements' class of the page object cannot be returned");
        }

        this.elementsClass = c;
        return (T) this.elements;
    }


    /**
     * Sets the driver instance for the page object
     * Note: this method should only be invoked via reflection from the PageStore class, and should not be renamed
     * or invoked directly from derived classes. It should also be noted that the driver of a PageObject containing
     * a SearchContext instance cannot be set, as the PageStore does not cache these instances.
     *
     * @param driver The WebDriver instance to use
     */
    protected void setDriver(WebDriver driver) {
        this.driver = driver;
        this.elements.initialise(this.driver);

        // reset 'elements' instance for all superclasses
        Class c = this.getClass();

        while (!c.equals(PageObjectBase.class)) {
            try {
                Field controls = c.getField("controls");
                controls.set(this, getElements(elementsClass));
            } catch (Exception ex) {
                // log the message
            }

            c = c.getSuperclass();
        }
    }


    /**
     * Inner class containing the interactable elements on the page
     */
    public static class Elements {

        protected WebDriver driver;
        protected SearchContext context;

        /**
         * Base constructor for the Elements class
         */
        public Elements() {
            this.driver = null;
            this.context = null;
        }

        /**
         * Initializes the Elements contained in the class using the Selenium PageFactory
         *
         * @param driver The main WebDriver instance for the class
         *               or WebDriver for resolution from the root of the dom)
         */
        public void initialise(WebDriver driver) {

            this.driver = driver;
            this.context = context;
            PageFactory.initElements(driver, this);
        }

    }

}
