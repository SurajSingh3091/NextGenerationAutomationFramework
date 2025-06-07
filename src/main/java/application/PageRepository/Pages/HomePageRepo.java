package application.PageRepository.Pages;


import application.PageRepository.PageInitializer.PageInitializer;
import com.nextZen.Framework.Context.ExecutionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;



public class HomePageRepo extends PageInitializer {
    ExecutionContext executionContext;
    //constructor
    public HomePageRepo(ExecutionContext context){
        super(context.getDriverManager().getDriver());
        this.executionContext=context;
    }
    
    @FindAll({@FindBy(xpath="//div[@class='category-cards']/child::div//h5")})
    private List<WebElement> homePageElements;



    public void clickDifferentSectionsOnHomePage(String sectionName){
        executionContext.getSeleniumHelper().waitForVisibilityOfAllElements("HomePageElements",homePageElements);
           for(WebElement ele:homePageElements){
               if(ele.getText().equals(sectionName)){
                   executionContext.getSeleniumHelper().clickWebElement(sectionName,ele);
               }
           }


    }

}
