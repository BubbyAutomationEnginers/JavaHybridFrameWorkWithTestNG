package GenericUtilies.GenericMethods.CommonHelpers;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class ShadowDomElementActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    // Constructor to initialize WebDriver, WebDriverWait, and JavascriptExecutor
    public ShadowDomElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    // 1. Get the shadow root of an element
    public WebElement getShadowRoot(WebElement hostElement) {
        try {
            return (WebElement) jsExecutor.executeScript("return arguments[0].shadowRoot", hostElement);
        } catch (Exception e) {
            System.out.println("Failed to get shadow root: " + e.getMessage());
            return null;
        }
    }

    // 2. Find a single element inside a Shadow DOM
    public WebElement findElementInShadowDom(WebElement shadowHost, String cssSelector) {
        try {
            WebElement shadowRoot = getShadowRoot(shadowHost);
            if (shadowRoot == null) {
                System.out.println("Shadow root not found for host element");
                return null;
            }
            WebElement element = shadowRoot.findElement(org.openqa.selenium.By.cssSelector(cssSelector));
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            System.out.println("Failed to find element in Shadow DOM with selector: " + cssSelector + " - " + e.getMessage());
            return null;
        }
    }
    // 3. Find multiple elements inside a Shadow DOM
    public List<WebElement> findElementsInShadowDom(WebElement shadowHost, String cssSelector) {
        try {
            WebElement shadowRoot = getShadowRoot(shadowHost);
            if (shadowRoot == null) {
                System.out.println("Shadow root not found for host element");
                return null;
            }
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(org.openqa.selenium.By.cssSelector(cssSelector)));
        } catch (Exception e) {
            System.out.println("Failed to find elements in Shadow DOM with selector: " + cssSelector + " - " + e.getMessage());
            return null;
        }
    }

    // 4. Click an element inside a Shadow DOM
    public void clickElementInShadowDom(WebElement shadowHost, String cssSelector) {
        try {
            WebElement element = findElementInShadowDom(shadowHost, cssSelector);
            if (element != null) {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } else {
                System.out.println("Element not found for clicking in Shadow DOM: " + cssSelector);
            }
        } catch (Exception e) {
            System.out.println("Failed to click element in Shadow DOM: " + cssSelector + " - " + e.getMessage());
        }
    }

    // 5. Send keys to an element inside a Shadow DOM
    public void sendKeysToElementInShadowDom(WebElement shadowHost, String cssSelector, String text) {
        try {
            WebElement element = findElementInShadowDom(shadowHost, cssSelector);
            if (element != null) {
                WebElement target = wait.until(ExpectedConditions.visibilityOf(element));
                target.clear();
                target.sendKeys(text);
            } else {
                System.out.println("Element not found for sending keys in Shadow DOM: " + cssSelector);
            }
        } catch (Exception e) {
            System.out.println("Failed to send keys to element in Shadow DOM: " + cssSelector + " - " + e.getMessage());
        }
    }

    // 6. Get text from an element inside a Shadow DOM
    public String getTextFromElementInShadowDom(WebElement shadowHost, String cssSelector) {
        try {
            WebElement element = findElementInShadowDom(shadowHost, cssSelector);
            if (element != null) {
                return wait.until(ExpectedConditions.visibilityOf(element)).getText();
            } else {
                System.out.println("Element not found for getting text in Shadow DOM: " + cssSelector);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get text from element in Shadow DOM: " + cssSelector + " - " + e.getMessage());
            return null;
        }
    }

    // 7. Check if an element is displayed inside a Shadow DOM
    public boolean isElementDisplayedInShadowDom(WebElement shadowHost, String cssSelector) {
        try {
            WebElement element = findElementInShadowDom(shadowHost, cssSelector);
            return element != null && wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            System.out.println("Element not displayed in Shadow DOM: " + cssSelector + " - " + e.getMessage());
            return false;
        }
    }

    // 8. Handle nested Shadow DOMs (find element in a nested shadow root)
    public WebElement findElementInNestedShadowDom(WebElement shadowHost, String... cssSelectors) {
        try {
            WebElement currentElement = shadowHost;
            for (String selector : cssSelectors) {
                WebElement shadowRoot = getShadowRoot(currentElement);
                if (shadowRoot == null) {
                    System.out.println("Shadow root not found for selector: " + selector);
                    return null;
                }
                currentElement = shadowRoot.findElement(org.openqa.selenium.By.cssSelector(selector));
            }
            return wait.until(ExpectedConditions.visibilityOf(currentElement));
        } catch (Exception e) {
            System.out.println("Failed to find element in nested Shadow DOM: " + e.getMessage());
            return null;
        }
    }

    // 9. Click an element in a nested Shadow DOM
    public void clickElementInNestedShadowDom(WebElement shadowHost, String... cssSelectors) {
        try {
            WebElement element = findElementInNestedShadowDom(shadowHost, cssSelectors);
            if (element != null) {
                wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            } else {
                System.out.println("Element not found in nested Shadow DOM for clicking");
            }
        } catch (Exception e) {
            System.out.println("Failed to click element in nested Shadow DOM: " + e.getMessage());
        }
    }

    // 10. Send keys to an element in a nested Shadow DOM
    public void sendKeysToElementInNestedShadowDom(WebElement shadowHost, String text, String... cssSelectors) {
        try {
            WebElement element = findElementInNestedShadowDom(shadowHost, cssSelectors);
            if (element != null) {
                WebElement target = wait.until(ExpectedConditions.visibilityOf(element));
                target.clear();
                target.sendKeys(text);
            } else {
                System.out.println("Element not found in nested Shadow DOM for sending keys");
            }
        } catch (Exception e) {
            System.out.println("Failed to send keys to element in nested Shadow DOM: " + e.getMessage());
        }
    }

    // 11. Get text from an element in a nested Shadow DOM
    public String getTextFromElementInNestedShadowDom(WebElement shadowHost, String... cssSelectors) {
        try {
            WebElement element = findElementInNestedShadowDom(shadowHost, cssSelectors);
            if (element != null) {
                return wait.until(ExpectedConditions.visibilityOf(element)).getText();
            } else {
                System.out.println("Element not found in nested Shadow DOM for getting text");
                return null;
            }
        } catch (Exception e) {
            System.out.println("Failed to get text from element in nested Shadow DOM: " + e.getMessage());
            return null;
        }
    }

    // 12. Check if an element exists in a Shadow DOM
    public boolean doesElementExistInShadowDom(WebElement shadowHost, String cssSelector) {
        try {
            WebElement element = findElementInShadowDom(shadowHost, cssSelector);
            return element != null;
        } catch (Exception e) {
            System.out.println("Element does not exist in Shadow DOM: " + cssSelector + " - " + e.getMessage());
            return false;
        }
    }

}
