package StepDefinitions;

import AllureTestListners.ListnerrsForAllure;
import GenericUtilies.BrowserFactory.BrowserFactory;
import GenericUtilies.GenericMethods.CommonHelpers.BrowserActionsHelper;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import static GenericUtilies.BrowserFactory.BrowserFactory.setUpBrowser;
@Listeners({ListnerrsForAllure.class})
public class TestCaess {
    private  WebDriver driver;


    @Parameters("browser")
    @BeforeTest
    public void launchingBrowser(String browser){
        driver= BrowserFactory.setUpBrowser(browser).createBrowser();
        driver.manage().window().maximize();
    }

    @Test
    public void login(){
        BrowserActionsHelper browserActionsHelper= new BrowserActionsHelper(driver);
        browserActionsHelper.navigateTo("https://naveenautomationlabs.com/opencart/index.php");
//        driver.get("https://naveenautomationlabs.com/opencart/index.php");
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
