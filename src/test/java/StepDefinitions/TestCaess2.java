package StepDefinitions;

import AllureTestListners.ListnerrsForAllure;
import GenericUtilies.BrowserFactory.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners({ListnerrsForAllure.class})
public class TestCaess2 {
    private  WebDriver driver;


    @Parameters("browser")
    @BeforeTest
    public void launchingBrowser(String browser){

        driver= BrowserFactory.setUpBrowser(browser).createBrowser();
        driver.manage().window().maximize();
    }

    @Test
    public void login(){
        driver.get("https://naveenautomationlabs.com/opencart/index.php");

        driver.findElement(By.id("logo")).click();
       String name= driver.findElement(By.className("dropdown")).getText();
        Assert.assertEquals("name","name");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @AfterTest
    public void browserTerminator(){
        driver.quit();
    }
}
