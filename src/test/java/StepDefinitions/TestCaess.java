package StepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static GenericUtilies.BrowserFactory.BrowserFactory.setUpBrowser;

public class TestCaess {
    private  WebDriver browser;
    @BeforeTest
    public void launchingBrowser(){
        browser=setUpBrowser("chrome").createBrowser();
        browser.manage().window().maximize();
    }

    @Test
    public void login(){
//        System.getProperties().list(System.out);
    }
}
