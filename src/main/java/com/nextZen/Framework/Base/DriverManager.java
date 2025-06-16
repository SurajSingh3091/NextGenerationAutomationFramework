package com.nextZen.Framework.Base;

import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import com.nextZen.Framework.WebUtility.SeleniumHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Collections;

/* Debugger for 10 seconds
setTimeout(() => { debugger; }, 10000);
*/
public class DriverManager {

    private ExecutionContext executionContext;
    public WebDriver driver;
    public SeleniumHelper seleniumHelper;
    public String browser;
    public String url;

    Logger logger = LogManager.getLogger(DriverManager.class);

    public DriverManager(ExecutionContext context) {
        this.executionContext = context;
    }

    public void setExecutionProperties() {
        executionContext.getConfigurationManager().loadPropertyFile("TestConfig", "test");
        browser = executionContext.getConfigurationManager().getProperty("browserName");
        url = executionContext.getConfigurationManager().getProperty("applicationUrl");
    }

    public void invokeWebApplication() {
        setExecutionProperties();
        driver = switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeDriver(setChromeOptions());
            case "edge" -> new EdgeDriver(setEdgeOptions());
            default -> throw (new IllegalArgumentException("Invalid Browser : " + browser));
        };
        driver.get(url);
        driver.manage().window().maximize();
        logger.info("Successfully launched the URL : " + url + " on Browser : " + browser);
        executionContext.getSeleniumHelper().waitForPageLoad();
    }

    public ChromeOptions setChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--start-maximized", //Launches the browser window in full screen. Ensures UI elements aren't hidden due to smaller window sizes — avoids viewport-based UI errors.
                "--disable-extensions", //Turns off Chrome extensions to reduce startup time and prevent unexpected UI interactions or extension popups.
                "--disable-popup-blocking", //Allows pop-ups, which are often part of test scenarios like login popups, file downloads, or alerts.
                // "--disable-infobars", //Removes the “Chrome is being controlled by automated test software” banner. Ensures clean screenshots and improves stealth.
                "--disable-notifications",//Suppresses browser-level push notifications. Prevents modal popups from interrupting test flow. Though we have blocke dit but it is important for some of the application, I have used it in my last project
                "--incognito",  //Launches Chrome in incognito mode — clears cache, cookies, and session data between runs. Improves test isolation and idempotency.
                "--disable-geolocation"//Prevents location prompts (e.g., "Allow this site to access your location"). Ensures consistent geolocation behavior across tests.
        );
        // "--disable-infobars" this property is deprecated now so using below method to remove the banner
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        logger.info("Following chrome options are set:\n"
                + "--start-maximized\n"
                + "--disable-extensions\n"
                + "--disable-popup-blocking\n"
                + "--disable-notifications\n"
                + "--incognito\n"
                + "--disable-geolocation\n"
                + "--remove automation banner");
        return chromeOptions;
    }

    public EdgeOptions setEdgeOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments(
                "--start-maximized", //Launches the browser window in full screen. Ensures UI elements aren't hidden due to smaller window sizes — avoids viewport-based UI errors.
                "--disable-extensions", //Turns off Chrome extensions to reduce startup time and prevent unexpected UI interactions or extension popups.
                "--disable-popup-blocking", //Allows pop-ups, which are often part of test scenarios like login popups, file downloads, or alerts.
                "--disable-notifications",//Suppresses browser-level push notifications. Prevents modal popups from interrupting test flow. Though we have blocke dit but it is important for some of the application, I have used it in my last project
                "--incognito",  //Launches Chrome in incognito mode — clears cache, cookies, and session data between runs. Improves test isolation and idempotency.
                "--disable-geolocation"//Prevents location prompts (e.g., "Allow this site to access your location"). Ensures consistent geolocation behavior across tests.
        );
        // "--disable-infobars" this property is deprecated now so using below method to remove the banner
        edgeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        logger.info("Following edge options are set:\n"
                + "--start-maximized\n"
                + "--disable-extensions\n"
                + "--disable-popup-blocking\n"
                + "--disable-notifications\n"
                + "--incognito\n"
                + "--disable-geolocation\n"
                + "--remove automation banner");
        return edgeOptions;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void killDriverInstance() {
        try {
            driver.quit();
            logger.info("Successfully quit the browser instance.");
        } catch (Exception exception) {
            logger.info("Unable to quit the driver instance!");
        } finally {
            if (driver != null) {
                driver.quit();
                logger.info("Successfully quit the browser instance, in Finally block.");
            }

        }
    }


}