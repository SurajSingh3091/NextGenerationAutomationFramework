package application.PageRepository.PageObjectManager;

import application.PageRepository.Pages.HomePageRepo;
import com.nextZen.Framework.Context.ExecutionContext;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private WebDriver driver;
    private ExecutionContext executionContext;

    // Page instances
   private HomePageRepo homePageRepo;

    public PageObjectManager(ExecutionContext context) {
        this.executionContext=context;
        this.driver = executionContext.getDriverManager().getDriver();

    }

    public HomePageRepo getHomePageRepo(){
        if(homePageRepo==null) {
            homePageRepo = new HomePageRepo(executionContext);
        }
        return homePageRepo;
    }
}
