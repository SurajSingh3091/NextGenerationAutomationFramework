package application.PageRepository.Pages;


import application.PageRepository.PageInitializer.PageInitializer;
import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Login_Page extends PageInitializer {

    ExecutionContext executionContext;

    Logger logger = LogManager.getLogger(Login_Page.class);

    public Login_Page(ExecutionContext context) {
        super(context.getDriverManager().getDriver());
        this.executionContext = context;
    }

    @FindBy(xpath = "//img[@alt='company-branding']")
    private WebElement loginPageLogo;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userNameTextBox;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement userPasswordTextBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;


    public boolean isLoginPageLogoVisible() {
        executionContext.getSeleniumHelper().waitForVisibilityOfElement("Logo On Login Page", loginPageLogo);
        if (loginPageLogo.isDisplayed()) {
            logger.info("The Logo On the login page is visible.");
            return true;
        } else {
            logger.error("The Logo On the login page is not visible.");
            return false;
        }
    }

    public void captureUsername(String userName) {
        try {
            executionContext.getSeleniumHelper().inputText("User Name", userNameTextBox, userName);
            logger.info("Successfully captured User Name as : " + userName);
        } catch (WebDriverException e) {
            logger.error("Failed to capture the user name: " + e.getMessage());
        }
    }

    public void capturePassword(String password) {
        try {
            executionContext.getSeleniumHelper().inputText("User password", userPasswordTextBox, password);
            logger.info("Successfully captured password.");
        } catch (WebDriverException e) {
            logger.error("Failed to capture the password: " + e.getMessage());
        }
    }

    public void clickLoginButton() {
        try {
            executionContext.getSeleniumHelper().clickWebElement("Login", loginButton);
            logger.info("Successfully clicked on Login button!.");
        } catch (WebDriverException e) {
            logger.error("Failed to click the login button: " + e.getMessage());
        }
    }


}
