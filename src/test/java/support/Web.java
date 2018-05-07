package support;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "browserStackCredentials.csv")
public class Web {
    //Set your credentials from Browser Stack website to execute the tests on cloud
    public static final String USERNAME = "typeBrowserStackUser";
    public static final String AUTOMATE_KEY = "typeBrowserStackPassword";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver createChrome(){
        //Open the browser - Set your chromedriver path to execute the tests on local machine
        System.setProperty("webdriver.chrome.driver", "C:\\env_folder\\chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        //browser.manage().window().maximize();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Navigate to Taskit page
        browser.get("http://www.juliodelima.com.br/taskit/");

        return browser;
    }

    public static WebDriver createBrowserStack() {
        //Include the environment config chosen on browser stack website
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "60.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1280x800");
        caps.setCapability("browserstack.debug", "true");

        WebDriver browser = null;

        try {
            browser = new RemoteWebDriver(new URL(URL), caps);
            browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            //Navigate to Taskit page
            browser.get("http://www.juliodelima.com.br/taskit/");
        } catch (MalformedURLException e) {
            System.out.println("There were problems with the URL: " + e.getMessage());
        }

        return browser;
    }
}
