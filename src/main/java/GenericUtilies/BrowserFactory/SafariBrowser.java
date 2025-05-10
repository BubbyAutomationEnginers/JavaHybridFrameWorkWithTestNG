package GenericUtilies.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariBrowser implements Browser{
    @Override
    public WebDriver createBrowser() {
        return new SafariDriver();
    }
}
