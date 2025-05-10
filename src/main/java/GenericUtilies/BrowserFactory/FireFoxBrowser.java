package GenericUtilies.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxBrowser implements Browser{
    @Override
    public WebDriver createBrowser() {
        return new FirefoxDriver();
    }
}
