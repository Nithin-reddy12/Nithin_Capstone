package testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import pages.CategoryPage;
import utilities.ExtentReportManager;

public class CategoryTest extends BaseClass {
    CategoryPage categoryPage;
    CartPage cartPage;

    @Parameters("browser") // Receive browser parameter from testng.xml
    @BeforeMethod
    public void setUp(String browser) throws IOException {
        try {
            invokeBrowser(browser); // Invoke browser from BaseClass
            categoryPage = new CategoryPage(getDriver());
            cartPage = new CartPage(getDriver(), prop);
        } catch (Exception e) {
            System.err.println("Error during setup: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 1)
    public void testAddingProductsFromDifferentCategories() throws IOException, InterruptedException {
        ExtentReportManager.startTest("Category Test");

        try {
            // Selecting "Laptops" and adding a product
            categoryPage.selectCategoryAndAddFirstProduct("Laptops");

            // Selecting "Phones" and adding a product
            categoryPage.selectCategoryAndAddFirstProduct("Phones");

            // Selecting "Monitors" and adding a product
            categoryPage.selectCategoryAndAddFirstProduct("Monitors");

            // Going to the Cart to check whether products are added or not
            categoryPage.goToCart();
            Thread.sleep(2000);

            ExtentReportManager.logPass("Successfully added products from different categories.");
        } catch (Exception e) {
            ExtentReportManager.logFail("Test failed: " + e.getMessage());
            System.err.println("Exception occurred during test: " + e.getMessage());
            throw e;
        }
    }

    @AfterMethod
    public void tearDown() {
        try {
            closeBrowser(); // This is from BaseClass to close the browser after test
            ExtentReportManager.endTest();
        } catch (Exception e) {
            System.err.println("Error during teardown: " + e.getMessage());
        }
    }
}
