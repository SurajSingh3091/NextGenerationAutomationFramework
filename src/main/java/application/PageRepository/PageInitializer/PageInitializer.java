package application.PageRepository.PageInitializer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageInitializer {

    WebDriver driver;

    public PageInitializer(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
