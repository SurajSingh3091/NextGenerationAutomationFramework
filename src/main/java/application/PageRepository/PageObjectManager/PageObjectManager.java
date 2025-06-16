package application.PageRepository.PageObjectManager;

import application.PageRepository.Pages.AddEmployee_Page;
import application.PageRepository.Pages.ApplicationMaster_Page;
import application.PageRepository.Pages.Home_Page;
import application.PageRepository.Pages.Login_Page;
import com.nextZen.Framework.ExecutionContext.ExecutionContext;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    public ExecutionContext executionContext;

    // Page instances
    private Login_Page loginPage;
    private Home_Page homePage;
    private AddEmployee_Page addEmployeePage;
    private ApplicationMaster_Page applicationMasterPage;

    public PageObjectManager(ExecutionContext context) {
        this.executionContext = context;
    }

    public Login_Page getLoginPage() {
        if (loginPage == null) {
            loginPage = new Login_Page(executionContext);
        }
        return loginPage;
    }

    public Home_Page getHomePage() {
        if (homePage == null) {
            homePage = new Home_Page(executionContext);
        }
        return homePage;
    }

    public AddEmployee_Page getAddEmployeePage() {
        if (addEmployeePage == null) {
            addEmployeePage = new AddEmployee_Page(executionContext);
        }
        return addEmployeePage;
    }

    public ApplicationMaster_Page getApplicationMasterPage() {
        if (applicationMasterPage == null) {
            applicationMasterPage = new ApplicationMaster_Page(executionContext);
        }
        return applicationMasterPage;
    }
}
