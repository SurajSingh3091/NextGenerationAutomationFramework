package application.PageRepository.PageInitializer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageInitializer {


    protected WebDriver driver;

    public PageInitializer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
