package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import support.Web;

import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "UserInfoPageObjectsTestData.csv")
public class UserInfoPageObjectsTest {
    private WebDriver browser;

    @Before
    public void setUP(){
        browser = Web.createBrowserStack();
    }

    @Test
    public void testAddUserAdditionalInfo(
            @Param(name = "user")String user,
            @Param(name = "password")String password,
            @Param(name = "type")String type,
            @Param(name = "contact")String contact,
            @Param(name = "message")String expectedMessage
    ){
        String toastText = new LoginPage(browser)
                .clickSignIn()
                .logIn(user, password)
                .clickMe()
                .clickMoreDataAboutYou()
                .clickAddMoreDateAboutYouButton()
                .addContact(type, contact)
                .GetToastText();

        assertEquals(expectedMessage, toastText);
    }

    @After
    public void tearDown(){
        browser.quit();
    }
}
