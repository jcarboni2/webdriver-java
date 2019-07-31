package tests;

import static org.junit.Assert.assertEquals;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import support.Generator;
import support.Screenshot;
import support.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "UserInfoTestData.csv")
public class UserInfoTest {
    private WebDriver browser;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){

        browser = Web.createChrome();

        //Click on "Sign in" text link
        browser.findElement(By.linkText("Sign in")).click();

        //Identify a Login form
        WebElement formSignInBox = browser.findElement(By.id("signinbox"));

        //Type registered user on field with "login" name within "signinbox" form id
        formSignInBox.findElement(By.name("login")).sendKeys("username");

        //Type "123456" text on field with "password" name within "signinbox" form id
        formSignInBox.findElement(By.name("password")).sendKeys("123456");

        //Click on link with "SIGN IN" text
        browser.findElement(By.linkText("SIGN IN")).click();

        //Validate if "Hi, user" text is found inside of element with class "me"
        WebElement me = browser.findElement(By.className("me"));
        String textOnElementMe = me.getText();
        assertEquals("Hi, user", textOnElementMe);

        //Click on link with "me" class
        browser.findElement(By.className("me")).click();

        //Click on link with "MORE DATA ABOUT YOU" text
        browser.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAddUserAdditionalInfo(@Param(name = "type")String type,
                                          @Param(name = "contact")String contact,
                                          @Param(name = "message")String expectedMessage) {

        //Click on button through //button[data-target="addmoredata"] xpath
        browser.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        //Identify the addmoredata id form popup
        WebElement popupAddMoreData = browser.findElement(By.id("addmoredata"));

        //Choose "Phone" option on name "type" combo
        WebElement typeField = popupAddMoreData.findElement(By.name("type"));
        new Select(typeField).selectByVisibleText(type);

        //Type "+5511999911231" text on "contact" field
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contact);

        //Click on "Save" link on the popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Validate if "Your contact has been added!" text is found on "toast-container" id
        WebElement messagePop = browser.findElement(By.id("toast-container"));
        String message = messagePop.getText();
        assertEquals(expectedMessage, message);
    }

    /* Before execute this Data Driven Test, add contacts on respective page and include those data on
    "removeUserContact" of the UseInfoTestData.csv file */
    @Test
    public void removeUserContact(@Param(name = "contact")String contact,
                                  @Param(name = "message")String expectedMessage
    ){

        //Click on element by //span[text()="+5511999911234"]/following-sibling::a xpath
        browser.findElement(By.xpath("//span[text()=\"" + contact + "\"]/following-sibling::a")).click();

        //Confirm the javascript window
        browser.switchTo().alert().accept();

        //Validate if "Rest in peace, dear phone!" or " Rest in peace, dear email!" messages are presented
        WebElement messagePop = browser.findElement(By.id("toast-container"));
        String message = messagePop.getText();
        assertEquals(expectedMessage, message);

        //Set your path to storage the screenshot
        String screenshotFile  = "/GitHub_Repository/Projects/Courses/evidences/taskit/" + Generator.datetimeToFile() +
                test.getMethodName() + ".png";
        Screenshot.take(browser, screenshotFile );

        //Wait 10 seconds for the window to disappear
        WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(ExpectedConditions.stalenessOf(messagePop));

        //Click on "Logout" link text
        browser.findElement(By.linkText("Logout")).click();

    }

    @After
    public void tearDown(){
        //Close browser
        browser.quit();
    }
}