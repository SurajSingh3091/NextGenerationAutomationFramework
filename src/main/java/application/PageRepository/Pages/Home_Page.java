package application.PageRepository.Pages;

import application.PageRepository.PageInitializer.PageInitializer;
import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import com.nextZen.Framework.WebUtility.SeleniumHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class Home_Page extends PageInitializer {
    ExecutionContext executionContext;
    SeleniumHelper seleniumHelper;
    Logger logger = LogManager.getLogger(Home_Page.class);

    public Home_Page(ExecutionContext context) {
        super(context.getDriverManager().getDriver());
        this.executionContext = context;
        this.seleniumHelper = executionContext.getSeleniumHelper();
    }

    @FindBy(xpath = "//button[text()=' Add ']")
    private WebElement addButtonOnPIMPage;


    public void clickCategoryLink(String categoryName, String category) {
        WebElement categoryLinkWebElement = executionContext.getDriverManager().getDriver().findElement(By.xpath("//span[text()='" + category + "']/parent::a"));
        seleniumHelper.clickWebElement(categoryName, categoryLinkWebElement);
    }

    public void clickAddButton() {
        seleniumHelper.clickWebElement("ADD", addButtonOnPIMPage);
    }




}
