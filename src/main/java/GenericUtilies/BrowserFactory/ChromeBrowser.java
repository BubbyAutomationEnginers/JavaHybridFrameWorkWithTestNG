package GenericUtilies.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser implements Browser{
    @Override
    public WebDriver createBrowser() {
        return new ChromeDriver();
    }
}
