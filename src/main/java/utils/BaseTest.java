package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    @BeforeMethod
    public void setUp(java.lang.reflect.Method method) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://bstackdemo.com/");

        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {

            test.fail(result.getThrowable());

            // Capture screenshot
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            String screenshotPath = "test-output/screenshots/"
                    + result.getName() + ".png";

            try {
                Files.createDirectories(Paths.get("test-output/screenshots"));
                Files.copy(screenshot.toPath(),
                        Paths.get(screenshotPath));
                test.addScreenCaptureFromPath("../screenshots/"
                        + result.getName() + ".png");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped");
        }

        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}