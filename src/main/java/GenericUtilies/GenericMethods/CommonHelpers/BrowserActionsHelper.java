package GenericUtilies.GenericMethods.CommonHelpers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Set;

import java.time.Duration;

public class BrowserActionsHelper {
    private WebDriver driver;
    private JavascriptExecutor jsExecutor;

    // Constructor to initialize WebDriver and JavascriptExecutor
    public BrowserActionsHelper(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    // 1. Navigate to a URL
    public void navigateTo(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println("Failed to navigate to URL: " + url + " - " + e.getMessage());
        }
    }

    // 2. Refresh the current page
    public void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            System.out.println("Failed to refresh page: " + e.getMessage());
        }
    }

    // 3. Navigate back
    public void navigateBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            System.out.println("Failed to navigate back: " + e.getMessage());
        }
    }

    // 4. Navigate forward
    public void navigateForward() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            System.out.println("Failed to navigate forward: " + e.getMessage());
        }
    }

    // 5. Get current URL
    public String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            System.out.println("Failed to get current URL: " + e.getMessage());
            return null;
        }
    }

    // 6. Get page title
    public String getPageTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            System.out.println("Failed to get page title: " + e.getMessage());
            return null;
        }
    }

    // 7. Maximize browser window
    public void maximizeWindow() {
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("Failed to maximize window: " + e.getMessage());
        }
    }

    // 8. Minimize browser window
    public void minimizeWindow() {
        try {
            driver.manage().window().minimize();
        } catch (Exception e) {
            System.out.println("Failed to minimize window: " + e.getMessage());
        }
    }

    // 9. Set browser window size
    public void setWindowSize(int width, int height) {
        try {
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
        } catch (Exception e) {
            System.out.println("Failed to set window size: " + width + "x" + height + " - " + e.getMessage());
        }
    }

    // 10. Switch to a new window or tab
    public void switchToWindow(String windowHandle) {
        try {
            driver.switchTo().window(windowHandle);
        } catch (NoSuchWindowException e) {
            System.out.println("Failed to switch to window: " + windowHandle + " - " + e.getMessage());
        }
    }

    // 11. Switch to the new window/tab (automatically picks the latest one)
    public void switchToNewWindow() {
        try {
            String currentWindow = driver.getWindowHandle();
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(currentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to switch to new window: " + e.getMessage());
        }
    }

    // 12. Get all window handles
    public Set<String> getAllWindowHandles() {
        try {
            return driver.getWindowHandles();
        } catch (Exception e) {
            System.out.println("Failed to get window handles: " + e.getMessage());
            return null;
        }
    }

    // 13. Close the current window
    public void closeCurrentWindow() {
        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("Failed to close current window: " + e.getMessage());
        }
    }

    // 14. Add a cookie
    public void addCookie(String name, String value) {
        try {
            Cookie cookie = new Cookie(name, value);
            driver.manage().addCookie(cookie);
        } catch (Exception e) {
            System.out.println("Failed to add cookie: " + name + " - " + e.getMessage());
        }
    }

    // 15. Get a cookie by name
    public Cookie getCookie(String name) {
        try {
            return driver.manage().getCookieNamed(name);
        } catch (Exception e) {
            System.out.println("Failed to get cookie: " + name + " - " + e.getMessage());
            return null;
        }
    }

    // 16. Delete a cookie by name
    public void deleteCookie(String name) {
        try {
            driver.manage().deleteCookieNamed(name);
        } catch (Exception e) {
            System.out.println("Failed to delete cookie: " + name + " - " + e.getMessage());
        }
    }

    // 17. Delete all cookies
    public void deleteAllCookies() {
        try {
            driver.manage().deleteAllCookies();
        } catch (Exception e) {
            System.out.println("Failed to delete all cookies: " + e.getMessage());
        }
    }

    // 18. Take a screenshot and save to file
    public void takeScreenshot(String filePath) {
        try {
            TakesScreenshot screenshot = ((TakesScreenshot) driver);
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot to: " + filePath + " - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    // 19. Execute JavaScript
    public Object executeJavaScript(String script, Object... args) {
        try {
            return jsExecutor.executeScript(script, args);
        } catch (Exception e) {
            System.out.println("Failed to execute JavaScript: " + script + " - " + e.getMessage());
            return null;
        }
    }

    // 20. Switch to a frame by index
    public void switchToFrame(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (Exception e) {
            System.out.println("Failed to switch to frame by index: " + index + " - " + e.getMessage());
        }
    }

    // 21. Switch to a frame by name or ID
    public void switchToFrame(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (Exception e) {
            System.out.println("Failed to switch to frame: " + nameOrId + " - " + e.getMessage());
        }
    }

    // 22. Switch to default content (out of frame)
    public void switchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            System.out.println("Failed to switch to default content: " + e.getMessage());
        }
    }

    // 23. Get page source
    public String getPageSource() {
        try {
            return driver.getPageSource();
        } catch (Exception e) {
            System.out.println("Failed to get page source: " + e.getMessage());
            return null;
        }
    }

    // 24. Set implicit wait timeout
    public void setImplicitWait(long timeoutInSeconds) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeoutInSeconds));
        } catch (Exception e) {
            System.out.println("Failed to set implicit wait: " + timeoutInSeconds + " - " + e.getMessage());
        }
    }

    // 25. Set page load timeout
    public void setPageLoadTimeout(long timeoutInSeconds) {
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeoutInSeconds));
        } catch (Exception e) {
            System.out.println("Failed to set page load timeout: " + timeoutInSeconds + " - " + e.getMessage());
        }
    }

    // 26. Set script execution timeout
    public void setScriptTimeout(long timeoutInSeconds) {
        try {
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(timeoutInSeconds));
        } catch (Exception e) {
            System.out.println("Failed to set script timeout: " + timeoutInSeconds + " - " + e.getMessage());
        }
    }

    // 27. Open a new tab
    public void openNewTab() {
        try {
            jsExecutor.executeScript("window.open('about:blank','_blank');");
            switchToNewWindow();
        } catch (Exception e) {
            System.out.println("Failed to open new tab: " + e.getMessage());
        }
    }

    // 28. Get browser window size
    public org.openqa.selenium.Dimension getWindowSize() {
        try {
            return driver.manage().window().getSize();
        } catch (Exception e) {
            System.out.println("Failed to get window size: " + e.getMessage());
            return null;
        }
    }

    // 29. Get browser window position
    public org.openqa.selenium.Point getWindowPosition() {
        try {
            return driver.manage().window().getPosition();
        } catch (Exception e) {
            System.out.println("Failed to get window position: " + e.getMessage());
            return null;
        }
    }

    // 30. Quit the browser
    public void quitBrowser() {
        try {
            driver.quit();
        } catch (Exception e) {
            System.out.println("Failed to quit browser: " + e.getMessage());
        }
    }
}
