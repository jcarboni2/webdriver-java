package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MePage extends BasePage {
    public MePage(WebDriver browser) {
        super(browser);
    }

    public MePage clickMoreDataAboutYou(){
        browser.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        return this;
    }

    public AddContactPage clickAddMoreDateAboutYouButton(){
        browser.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        return new AddContactPage(browser);
    }
}
