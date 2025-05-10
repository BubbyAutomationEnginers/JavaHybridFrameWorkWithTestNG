package GenericUtilies.BrowserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeBrowser implements Browser{
    @Override
    public WebDriver createBrowser() {
        return new EdgeDriver();
    }
}
