package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import utilities.ExtentReportManager;

public class CartTest extends BaseClass {
    private CartPage cartPage;

    @Test(priority = 1)
    @Parameters("browser") // Get browser from TestNG XML
    public void testOpenCart(String browser) throws IOException {
        ExtentReportManager.startTest("Cart Test"); // Start Extent Report Test
        invokeBrowser(browser);
        cartPage = new CartPage(getDriver(), prop);
        cartPage.openCart();
        ExtentReportManager.logInfo("Opened Cart page");
        ExtentReportManager.endTest();
    }

    @Test(priority = 2, dependsOnMethods = "testOpenCart")
    public void testPlaceOrder() {
        ExtentReportManager.startTest("Place Order Test");
        cartPage.openCart();
        ExtentReportManager.logInfo("Opened Cart page");
        cartPage.clickPlaceOrder();
        ExtentReportManager.logInfo("Clicked on Place Order button");
        cartPage.placeOrder();
        ExtentReportManager.logInfo("Order placed successfully with test data");
        Assert.assertTrue(cartPage.verifyOrderPlaced(), "Order placement failed!");
        ExtentReportManager.endTest();
    }

    @AfterClass
    public void tearDown() {
        closeBrowser();
        ExtentReportManager.endTest(); // Ensure the last test is ended
    }
}
