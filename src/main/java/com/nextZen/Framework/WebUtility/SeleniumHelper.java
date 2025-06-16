package com.nextZen.Framework.WebUtility;

import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SeleniumHelper {
    public  ExecutionContext executionContext;
    private final WebDriverWait webDriverWait;
   Logger logger = LogManager.getLogger(SeleniumHelper.class);
    /**
     * Constructor
     *
     * @param context ExecutionContext instance
     */
    public SeleniumHelper(ExecutionContext context) {
        this.executionContext = context;
        Duration timeOut = Duration.ofSeconds(Integer.parseInt(executionContext.getConfigurationManager().getProperty("explicitWaitTimeOut")));
        webDriverWait = new WebDriverWait(executionContext.getDriverManager().getDriver(), timeOut);
    }

    /**
     * click WebElement
     *
     * @param elementName Element Name
     * @param element     WebElement
     */
    public void clickWebElement(String elementName, WebElement element) {
        try {
            waitForElementToBeClickable(elementName, element);
            element.click();
            logger.info("Successfully clicked on element: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + elementName + " Reason: " + e.getMessage());
        }
    }

    /**
     * input the text in text box
     *
     * @param elementName
     * @param element
     * @param value
     */
    public void inputText(String elementName, WebElement element, String value) {
        try {
            waitForElementToBeClickable(elementName, element);
            element.sendKeys(value);
            logger.info("Successfully clicked on element: " + elementName);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + elementName + " Reason: " + e.getMessage());
        }
    }

    /**
     * clear and input the text in test box
     *
     * @param elementName
     * @param element
     * @param value
     */
    public void clearAndInputText(String elementName, WebElement element, String value) {
        try {
            waitForElementToBeClickable(elementName, element);
            element.clear();
            logger.info("Successfully cleared the element: " + elementName);
            /*Sometimes we see the race condition between two operations on an element due to JS/AJAX call.For example clear and sendKeys.
             in this case we are waiting for the value tag to be either null or empty  after clear, just in case if any JS/AJAX call is there    */
            logger.info("Waiting for value tag to be empty or null!");
            webDriverWait.until(driver -> {
                String currentValue = element.getDomAttribute("value");
                return currentValue == null || currentValue.isEmpty();

            });
            logger.info("The value tag is Empty now!");
            element.sendKeys(value);
            logger.info("Successfully updated the element: " + elementName + "with value: " + value);
        } catch (Exception e) {
            logger.error("Failed to click on element: " + elementName + " Reason: " + e.getMessage());
        }
    }


    /**
     * waits for page to completely load along with the java scripts to complete
     */
    public void waitForPageLoad() {
        // 1. Wait for document.readyState to be 'complete'
        waitForDomToLoad();
        // 2. Optionally wait for jQuery to be inactive
        waitForAJAX();
    }

    /**
     * Wait for document.readyState to be 'complete'
     * Other value are:
     * ->loading : The browser is loading the document
     * ->interactive:The document has been parsed, but sub-resources (images, stylesheets, frames) are still loading
     * ->complete: The entire page (DOM + sub-resources) is fully loaded.
     */
    public void waitForDomToLoad() {
        try {
            ExpectedCondition<Boolean> documentReady = driver ->
                    ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            webDriverWait.until(documentReady);
            logger.info("Dom rendered successfully, the document is in Ready State.");
        } catch (Exception e) {
            logger.error("Failed to render the Dom in given time interval.");
        }
    }

    /**
     * AJAX communicate with the server in the background without reloading the entire page.
     * AJAX typically refers to:
     * ->Sending GET or POST HTTP requests from the browser
     * ->Receiving JSON or HTML responses
     * ->Updating part of the page based on response
     */
    public void waitForAJAX() {
        try {
            ExpectedCondition<Boolean> jQueryInactive = driver -> {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                try {
                    return (Boolean) js.executeScript("return !!window.jQuery && jQuery.active == 0");  //checking till active call to AJAX by JQuery is 0
                } catch (Exception jq) {
                    return true;// checking if jQuery is not present
                }
            };
            webDriverWait.until(jQueryInactive);
            logger.info("All the AJAX call completed after the action!!");
        } catch (Exception e) {
            logger.error("Some of the AJAX call is still in progress and web driver wait timed out.");
        }
    }

    /**
     * waits for the visibility of the web element
     *
     * @param elementName
     * @param element
     */
    public void waitForVisibilityOfElement(String elementName, WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            logger.info("Element '" + elementName + "' is visible.");
        } catch (Exception e) {
            logger.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
            throw new RuntimeException("Element not visible: " + elementName, e);
        }
    }

    /**
     * waits for the visibility of all the web element
     *
     * @param elementName
     * @param element
     */
    public void waitForVisibilityOfAllElements(String elementName, List<WebElement> element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));
            logger.info("Element '" + elementName + "' is visible.");
        } catch (Exception e) {
            logger.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
            throw new RuntimeException("Element not visible: " + elementName, e);
        }
    }

    /**
     * waits till the element is clickable
     *
     * @param elementName
     * @param element
     */
    public void waitForElementToBeClickable(String elementName, WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            logger.info("Element '" + elementName + "' is visible and enabled.");
        } catch (Exception e) {
            logger.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
            throw new RuntimeException("Element not visible and enabled: " + elementName, e);
        }
    }

    /**
     * waits till the element is clickable located via By class
     *
     * @param elementName
     * @param elementLocator
     */
    public void waitForElementToBeClickableByLocator(String elementName, By elementLocator) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elementLocator));
            logger.info("Element '" + elementName + "' is visible and enabled.");
        } catch (Exception e) {
            logger.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
            throw new RuntimeException("Element not visible and enabled: " + elementName, e);
        }
    }

    /**
     * waits till the element is clickable located via By class
     *
     * @param elementName
     * @param elementLocator
     */
    public void waitForFrameToBeAvailableAndSwitchToIt(String elementName, By elementLocator) {
        try {
            webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementLocator));
            logger.info("Frame '" + elementName + "' is visible and switched to it.");
        } catch (Exception e) {
            logger.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
            throw new RuntimeException("Element not visible: " + elementName, e);
        }
    }


    public boolean isElementDisplayed(String elementName, WebElement element) {
        try {
            waitForVisibilityOfElement(elementName, element);
            if (element.isDisplayed()) {
                logger.info("The Element: " + elementName + " is displayed.");
                return true;
            } else {
                logger.info("The Element: " + elementName + " is not displayed.");
                return false;
            }
        } catch (Exception e) {
            logger.error("Method failed with Exception: " + e.toString());
            throw new RuntimeException("Method failed with Exception :", e);
        }
    }

}


