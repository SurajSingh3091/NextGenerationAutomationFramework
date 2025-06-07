package com.nextZen.Framework.WebUtility;

import com.nextZen.Framework.ConfigReaderUtility.ConfigurationManager;
import com.nextZen.Framework.Context.ExecutionContext;
import com.nextZen.Framework.LoggerUtility.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumHelper {
    private ExecutionContext executionContext;
    private final WebDriverWait webDriverWait;

    /**
     * Constructor
     * @param context ExecutionContext instance
     */
    public SeleniumHelper(ExecutionContext context) {
        this.executionContext = context;
        Duration timeOut=  Duration.ofSeconds(Integer.parseInt(executionContext.getConfigurationManager().getProperty("explicitWaitTimeOut")));
        webDriverWait = new WebDriverWait(executionContext.getDriverManager().getDriver(),timeOut );
    }

    /**
     * click WebElement
     * @param elementName Element Name
     * @param element     WebElement
     */
    public void clickWebElement(String elementName, WebElement element) {
        try {
            waitForElementToBeClickable(elementName, element);
            element.click();
            Log.info("Successfully clicked on element: " + elementName);
        } catch (Exception e) {
            Log.error("Failed to click on element: " + elementName + " Reason: " + e.getMessage());
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
            Log.info("Successfully clicked on element: " + elementName);
        } catch (Exception e) {
            Log.error("Failed to click on element: " + elementName + " Reason: " + e.getMessage());
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
            /*Sometimes we see the race condition between two operations on an element due to JS/AJAX call.For example clear and sendKeys.
             in this case we are waiting for the value tag to be either null or empty  after clear, just in case if any JS/AJAX call is there    */
            webDriverWait.until(driver -> {
                String currentValue = element.getAttribute("value");
                return currentValue == null || currentValue.isEmpty();
            });
            element.sendKeys(value);
            Log.info("Successfully clicked on element: " + elementName);
        } catch (Exception e) {
            Log.error("Failed to click on element: " + elementName + " Reason: " + e.getMessage());
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
            Log.info("Dom rendered successfully, the document is in Ready State.");
        } catch (Exception e) {
            Log.error("Failed to render the Dom in given time interval.");
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
            Log.info("All the AJAX call completed after the action!!");
        } catch (Exception e) {
            Log.error("Some of the AJAX call is still in progress and web driver wait timed out.");
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
            Log.info("Element '" + elementName + "' is visible.");
        } catch (Exception e) {
            Log.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
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
            Log.info("Element '" + elementName + "' is visible.");
        } catch (Exception e) {
            Log.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
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
            Log.info("Element '" + elementName + "' is visible.");
        } catch (Exception e) {
            Log.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
            throw new RuntimeException("Element not visible: " + elementName, e);
        }
    }

    /**
     * waits till the element is clickable located via By class
     *
     * @param elementName
     * @param elementLocator
     */
    public boolean waitForElementToBeClickableByLocator(String elementName, By elementLocator) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(elementLocator));
            Log.info("Element '" + elementName + "' is visible.");
            return true;
        } catch (Exception e) {
            Log.error("Timeout waiting for visibility of element: '" + elementName + "'. Reason: " + e.getMessage());
            throw new RuntimeException("Element not visible: " + elementName, e);
        }
    }

}


