package GenericUtilies.GenericMethods.CommonHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;
import java.util.List;
public class ElementActions {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor jsExecutor;

    // Constructor to initialize WebDriver, WebDriverWait, Actions, and JavascriptExecutor
    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    // 1. Click an element
    public void clickElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (Exception e) {
            System.out.println("Failed to click element: " + locator + " - " + e.getMessage());
        }
    }

    // 2. Click an element using JavaScript (useful for elements not clickable via standard click)
    public void clickElementWithJS(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            jsExecutor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Failed to click element with JS: " + locator + " - " + e.getMessage());
        }
    }

    // 3. Send keys (enter text) to an element
    public void sendKeysToElement(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("Failed to send keys to element: " + locator + " - " + e.getMessage());
        }
    }

    // 4. Get text from an element
    public String getElementText(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getText();
        } catch (Exception e) {
            System.out.println("Failed to get text from element: " + locator + " - " + e.getMessage());
            return null;
        }
    }

    // 5. Check if an element is displayed
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (Exception e) {
            System.out.println("Element not displayed: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    // 6. Check if an element is enabled
    public boolean isElementEnabled(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isEnabled();
        } catch (Exception e) {
            System.out.println("Element not enabled: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    // 7. Check if an element is selected (e.g., checkbox or radio button)
    public boolean isElementSelected(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isSelected();
        } catch (Exception e) {
            System.out.println("Element not selected: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    // 8. Get an attribute value of an element
    public String getElementAttribute(By locator, String attributeName) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            System.out.println("Failed to get attribute: " + attributeName + " - " + e.getMessage());
            return null;
        }
    }

    // 9. Select dropdown option by visible text
    public void selectDropdownByText(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(text);
        } catch (Exception e) {
            System.out.println("Failed to select dropdown option: " + text + " - " + e.getMessage());
        }
    }

    // 10. Select dropdown option by index
    public void selectDropdownByIndex(By locator, int index) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.selectByIndex(index);
        } catch (Exception e) {
            System.out.println("Failed to select dropdown by index: " + index + " - " + e.getMessage());
        }
    }

    // 11. Select dropdown option by value
    public void selectDropdownByValue(By locator, String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.selectByValue(value);
        } catch (Exception e) {
            System.out.println("Failed to select dropdown by value: " + value + " - " + e.getMessage());
        }
    }

    // 12. Deselect dropdown option by visible text
    public void deselectDropdownByText(By locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.deselectByVisibleText(text);
        } catch (Exception e) {
            System.out.println("Failed to deselect dropdown option: " + text + " - " + e.getMessage());
        }
    }

    // 13. Deselect all options in a dropdown
    public void deselectAllDropdown(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Select dropdown = new Select(element);
            dropdown.deselectAll();
        } catch (Exception e) {
            System.out.println("Failed to deselect all dropdown options: " + locator + " - " + e.getMessage());
        }
    }

    // 14. Mouse hover over an element
    public void mouseHover(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            actions.moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Failed to hover over element: " + locator + " - " + e.getMessage());
        }
    }

    // 15. Drag and drop from source to target
    public void dragAndDrop(By sourceLocator, By targetLocator) {
        try {
            WebElement source = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
            WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(targetLocator));
            actions.dragAndDrop(source, target).perform();
        } catch (Exception e) {
            System.out.println("Failed to drag and drop: " + sourceLocator + " to " + targetLocator + " - " + e.getMessage());
        }
    }

    // 16. Right-click an element
    public void rightClickElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            actions.contextClick(element).perform();
        } catch (Exception e) {
            System.out.println("Failed to right-click element: " + locator + " - " + e.getMessage());
        }
    }

    // 17. Double-click an element
    public void doubleClickElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            actions.doubleClick(element).perform();
        } catch (Exception e) {
            System.out.println("Failed to double-click element: " + locator + " - " + e.getMessage());
        }
    }

    // 18. Send keyboard keys to an element
    public void sendKeyboardKeys(By locator, String keys) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            actions.sendKeys(element, keys).perform();
        } catch (Exception e) {
            System.out.println("Failed to send keyboard keys to element: " + locator + " - " + e.getMessage());
        }
    }

    // 19. Upload a file
    public void uploadFile(By locator, String filePath) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.sendKeys(filePath);
        } catch (Exception e) {
            System.out.println("Failed to upload file: " + filePath + " - " + e.getMessage());
        }
    }

    // 20. Handle alert - Accept
    public void acceptAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).accept();
        } catch (Exception e) {
            System.out.println("Failed to accept alert: " + e.getMessage());
        }
    }

    // 21. Handle alert - Dismiss
    public void dismissAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).dismiss();
        } catch (Exception e) {
            System.out.println("Failed to dismiss alert: " + e.getMessage());
        }
    }

    // 22. Get alert text
    public String getAlertText() {
        try {
            return wait.until(ExpectedConditions.alertIsPresent()).getText();
        } catch (Exception e) {
            System.out.println("Failed to get alert text: " + e.getMessage());
            return null;
        }
    }

    // 23. Send text to alert
    public void sendTextToAlert(String text) {
        try {
            wait.until(ExpectedConditions.alertIsPresent()).sendKeys(text);
        } catch (Exception e) {
            System.out.println("Failed to send text to alert: " + e.getMessage());
        }
    }

    // 24. Wait for element to disappear
    public boolean waitForElementToDisappear(By locator) {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (Exception e) {
            System.out.println("Element did not disappear: " + locator + " - " + e.getMessage());
            return false;
        }
    }

    // 25. Find multiple elements and return their count
    public int getElementsCount(By locator) {
        try {
            List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            return elements.size();
        } catch (Exception e) {
            System.out.println("Failed to get elements count: " + locator + " - " + e.getMessage());
            return 0;
        }
    }

    // 26. Scroll to an element
    public void scrollToElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            System.out.println("Failed to scroll to element: " + locator + " - " + e.getMessage());
        }
    }

    // 27. Get CSS value of an element
    public String getCssValue(By locator, String cssProperty) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.getCssValue(cssProperty);
        } catch (Exception e) {
            System.out.println("Failed to get CSS value: " + cssProperty + " - " + e.getMessage());
            return null;
        }
    }

    // 28. Submit a form
    public void submitForm(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.submit();
        } catch (Exception e) {
            System.out.println("Failed to submit form: " + locator + " - " + e.getMessage());
        }
    }

    // 29. Check if element exists
    public boolean doesElementExist(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
