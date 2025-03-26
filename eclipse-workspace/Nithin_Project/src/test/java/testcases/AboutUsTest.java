package testcases;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.AboutUsPage;
import utilities.ExtentReportManager;

public class AboutUsTest extends BaseClass {
    AboutUsPage aboutUsPage;

    @Parameters("browser") // Receive browser parameter from testng.xml
    @BeforeMethod
    public void setUp(String browser) throws IOException {
        invokeBrowser(browser); // Pass the parameter to BaseClass method
        aboutUsPage = new AboutUsPage(getDriver()); // Use getDriver() instead of directly accessing driver
        ExtentReportManager.startTest("About Us Test");
    }

    @Test
    public void testAboutUsNavigation() throws IOException, InterruptedException {
        aboutUsPage.openAboutUs();
        screenshot("AboutUsPage"); // Take screenshot
        ExtentReportManager.logPass("Successfully navigated to the About Us page.");
    }

    @AfterMethod
    public void tearDownTest() throws InterruptedException {
        closeBrowser(); // Close the browser using BaseClass method
        ExtentReportManager.logInfo("Browser closed after test execution.");
        ExtentReportManager.endTest();
    }
}
