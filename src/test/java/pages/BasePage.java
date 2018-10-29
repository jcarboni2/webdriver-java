package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    protected WebDriver browser;

    public BasePage(WebDriver browser){
        this.browser = browser;
    }

    public String GetToastText(){
        return browser.findElement(By.id("toast-container")).getText();
    }
}