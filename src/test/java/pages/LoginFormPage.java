package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage{

    public LoginFormPage(WebDriver browser) {
        super(browser);
    }

    public LoginFormPage typeUser(String user){
        browser.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(user);

        return this;
    }

    public LoginFormPage typePassword(String password){
        browser.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(password);

        return this;
    }

    public SecretPage clickSignIn(){
        browser.findElement(By.linkText("SIGN IN")).click();

        return new SecretPage(browser);
    }

    public SecretPage logIn(String user, String password){
        typeUser(user);
        typePassword(password);
        clickSignIn();

        return new SecretPage(browser);
    }
}
