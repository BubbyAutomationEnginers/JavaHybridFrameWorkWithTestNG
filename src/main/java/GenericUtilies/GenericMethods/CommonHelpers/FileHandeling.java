package GenericUtilies.GenericMethods.CommonHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
public class FileHandeling {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static JavascriptExecutor jsExecutor;

    // Constructor to initialize WebDriver, WebDriverWait, and JavascriptExecutor
    public FileHandeling(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    // 1. Upload a file to a web element
    public static void uploadFile(By locator, String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new IOException("File does not exist: " + filePath);
            }
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            element.sendKeys(file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Failed to upload file: " + filePath + " to element: " + locator + " - " + e.getMessage());
        }
    }

    // 2. Verify if a file has been downloaded
    public static boolean isFileDownloaded(String downloadDir, String fileName, long timeoutInSeconds) {
        try {
            Path filePath = Paths.get(downloadDir, fileName);
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < timeoutInSeconds * 1000) {
                if (Files.exists(filePath)) {
                    return true;
                }
                Thread.sleep(500); // Wait 500ms before checking again
            }
            System.out.println("File not found in download directory: " + filePath);
            return false;
        } catch (Exception e) {
            System.out.println("Failed to verify file download: " + fileName + " - " + e.getMessage());
            return false;
        }
    }

    // 3. Take a screenshot and save to a file
    public static void takeScreenshot(String filePath) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Failed to save screenshot to: " + filePath + " - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }

    // 4. Read a text file (e.g., test data)
    public static List<String> readTextFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            System.out.println("Failed to read text file: " + filePath + " - " + e.getMessage());
            return null;
        }
    }

    // 5. Write to a text file (e.g., test results or logs)
    public static void writeToTextFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write to text file: " + filePath + " - " + e.getMessage());
        }
    }

    // 6. Append to a text file
    public static void appendToTextFile(String filePath, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to append to text file: " + filePath + " - " + e.getMessage());
        }
    }

    // 7. Delete a file
    public static boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            System.out.println("Failed to delete file: " + filePath + " - " + e.getMessage());
            return false;
        }
    }

    // 8. Clear a download directory
    public static void clearDownloadDirectory(String downloadDir) {
        try {
            File dir = new File(downloadDir);
            if (dir.exists() && dir.isDirectory()) {
                for (File file : dir.listFiles()) {
                    if (file.isFile()) {
                        Files.deleteIfExists(file.toPath());
                    }
                }
            } else {
                System.out.println("Download directory does not exist or is not a directory: " + downloadDir);
            }
        } catch (IOException e) {
            System.out.println("Failed to clear download directory: " + downloadDir + " - " + e.getMessage());
        }
    }

    // 9. Verify file contents (simple text comparison)
    public static boolean verifyFileContents(String filePath, String expectedContent) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return content.contains(expectedContent);
        } catch (IOException e) {
            System.out.println("Failed to verify file contents: " + filePath + " - " + e.getMessage());
            return false;
        }
    }

    // 10. Get file size
    public static long getFileSize(String filePath) {
        try {
            return Files.size(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("Failed to get file size: " + filePath + " - " + e.getMessage());
            return -1;
        }
    }

    // 11. Check if a file exists
    public static boolean doesFileExist(String filePath) {
        try {
            return Files.exists(Paths.get(filePath));
        } catch (Exception e) {
            System.out.println("Failed to check if file exists: " + filePath + " - " + e.getMessage());
            return false;
        }
    }

    // 12. Create a directory if it doesn't exist
    public static boolean createDirectory(String dirPath) {
        try {
            Path path = Paths.get(dirPath);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                return true;
            }
            return false;
        } catch (IOException e) {
            System.out.println("Failed to create directory: " + dirPath + " - " + e.getMessage());
            return false;
        }
    }

    // 13. Trigger file download using JavaScript (for links that don't use <a> tags)
    public static void triggerFileDownload(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            jsExecutor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Failed to trigger file download for element: " + locator + " - " + e.getMessage());
        }
    }
}
