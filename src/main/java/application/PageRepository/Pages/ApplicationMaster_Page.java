package application.PageRepository.Pages;

import application.PageRepository.PageInitializer.PageInitializer;
import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import com.nextZen.Framework.WebUtility.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ApplicationMaster_Page extends PageInitializer {
    public ExecutionContext executionContext;
    private SeleniumHelper seleniumHelper;
    Logger logger = LogManager.getLogger(ApplicationMaster_Page.class);

    public ApplicationMaster_Page(ExecutionContext context) {
        super(context.getDriverManager().getDriver());
        this.executionContext = context;
        this.seleniumHelper = executionContext.getSeleniumHelper();
    }

    @FindBy(xpath = "//button[text()=' Save ']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='oxd-toaster_1']/descendant::p[text()='Successfully Saved']")
    private WebElement successfullySaveMessage;

    public boolean isElementOnHomepageDisplayed(String text) {
        WebElement homePageTextWebElement = executionContext.getDriverManager().getDriver().findElement(By.xpath("//h6[text()='" + text + "']"));
        return seleniumHelper.isElementDisplayed(text, homePageTextWebElement) ? true : false;
    }

    public void clickSaveButton() {
        seleniumHelper.clickWebElement("Save", saveButton);
        logger.info("Successfully saved the employee detail");
    }

    public boolean isSaveSuccessfulMessageDisplayed() {
        return seleniumHelper.isElementDisplayed("Save Successful Message", successfullySaveMessage) ? true : false;
    }
}
