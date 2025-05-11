package StepDefinitions;

import AllureTestListners.ListnerrsForAllure;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import static GenericUtilies.BrowserFactory.BrowserFactory.setUpBrowser;
@Listeners({ListnerrsForAllure.class})
public class TestCaess {
    private  WebDriver browser;
    @BeforeTest
    public void launchingBrowser(){
        browser=setUpBrowser("chrome").createBrowser();
        browser.manage().window().maximize();
    }

    @Test
    public void login(){
        browser.get("https://naveenautomationlabs.com/opencart/index.php");

        browser.findElement(By.id("logo")).click();
       String name= browser.findElement(By.className("dropdown")).getText();
        Assert.assertEquals("name","grte");
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterTest
    public void browserTerminator(){
        browser.quit();
    }
}
