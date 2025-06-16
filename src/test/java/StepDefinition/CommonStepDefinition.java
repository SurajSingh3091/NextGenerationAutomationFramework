package StepDefinition;

import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonStepDefinition {
    public  ExecutionContext executionContext;
    Logger logger = LogManager.getLogger(CommonStepDefinition.class);

   public CommonStepDefinition(ExecutionContext context) {
        this.executionContext = context;
    }

    @Given("User is on login page he enters username and password and clicks on login button")
    public void user_is_on_login_page_he_enters_username_and_password_and_clicks_on_login_button() {
        String userName = executionContext.getConfigurationManager().getProperty("applicationUserName");
        String password = executionContext.getConfigurationManager().getProperty("applicationPassword");
        if (executionContext.getPageObjectManager().getLoginPage().isLoginPageLogoVisible()) {
            executionContext.getPageObjectManager().getLoginPage().captureUsername(userName);
            executionContext.getPageObjectManager().getLoginPage().capturePassword(password);
            executionContext.getPageObjectManager().getLoginPage().clickLoginButton();
            //Add reporter when developed.
        }
        executionContext.getSeleniumHelper().waitForPageLoad();
    }

    @Then("User should be navigated to home page of the application")
    public void user_should_be_navigated_to_home_page_of_the_application() {
        if (executionContext.getPageObjectManager().getApplicationMasterPage().isElementOnHomepageDisplayed("Dashboard")) {
            logger.info("Successfully logged in to the application");
            //Add reporter when developed.
        } else {
            logger.error("Failed to login to the application");
            //Add reporter when developed for failure with screenshot
        }
    }
}
