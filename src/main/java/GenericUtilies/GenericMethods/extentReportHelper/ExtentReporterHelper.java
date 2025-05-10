package GenericUtilies.GenericMethods.extentReportHelper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReporterHelper {

    public static void generateExtentReport(){
        ExtentReports extentReports= new ExtentReports();
        File extentReportFile= new File(System.getProperty("user.dir")+"testExtentReport.html");
        ExtentSparkReporter spark = new ExtentSparkReporter(extentReportFile);
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Test Hybrid frame work");
        spark.config().setDocumentTitle("test tilte opencart");
        spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReports.attachReporter(spark);
        extentReports.setSystemInfo("open cart","https://naveenautomationlabs.com/opencart/index.php");
        extentReports.setSystemInfo("email","venkat@yopmail.com");
        extentReports.setSystemInfo("OperatingSystem","windows");
        extentReports.setSystemInfo("BrowserType","Chrome");

    }
}
