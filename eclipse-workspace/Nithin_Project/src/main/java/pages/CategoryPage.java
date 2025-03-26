package pages;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExtentReportManager;

public class CategoryPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[text()='Laptops']")
    WebElement laptopsCategory;

    @FindBy(xpath = "//a[text()='Monitors']")
    WebElement monitorsCategory;

    @FindBy(xpath = "//a[text()='Phones']")
    WebElement phonesCategory;

    @FindBy(id = "cartur")
    WebElement cartLink;
    
    @FindBy(xpath = "//a[contains(text(), 'Home')]")
    WebElement homeLink;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Explicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit Wait
    }

    // ** Method to select category and add first product **
    public void selectCategoryAndAddFirstProduct(String category) throws IOException, InterruptedException {
        ExtentReportManager.logInfo("Selecting category: " + category);

        for (int attempt = 0; attempt < 2; attempt++) {
            try {
              
            	// Click on the category
                WebElement categoryElement = getCategoryElement(category);
                wait.until(ExpectedConditions.elementToBeClickable(categoryElement)).click();

                // Wait for category products to load (Ensuring DOM update)
                wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//div[@class='card-block']/h4/a"))));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='card-block']/h4/a")));

                // Fetch product list dynamically to avoid stale elements
                List<WebElement> freshProductList = driver.findElements(By.xpath("//div[@class='card-block']/h4/a"));
                if (freshProductList.isEmpty()) {
                    ExtentReportManager.logFail("No products found in category: " + category);
                    return;
                }
                freshProductList.get(0).click(); // Click the first product

                // Click 'Add to Cart'
                WebElement addToCartBtn = wait
                        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));
                addToCartBtn.click();

                // Handle alert
                waitForAlertAndAccept();
                ExtentReportManager.logPass("Product added to cart from " + category);
                
                homeLink.click();
                return; // Exit method after success
            } catch (org.openqa.selenium.StaleElementReferenceException e) {
                ExtentReportManager.logFail("Stale Element Exception, retrying: " + e.getMessage());
            } catch (org.openqa.selenium.NoSuchElementException | org.openqa.selenium.TimeoutException e) {
                ExtentReportManager.logFail("Retry due to missing element: " + e.getMessage());
            }
        }
    }

    // ** Method to return category element dynamically **
    public WebElement getCategoryElement(String category) {
        switch (category.toLowerCase()) {
            case "laptops":
                return laptopsCategory;
            case "monitors":
                return monitorsCategory;
            case "phones":
                return phonesCategory;
            default:
                throw new IllegalArgumentException("Invalid category: " + category);
        }
    }

    // ** Method to Handle Alerts **
    public void waitForAlertAndAccept() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            ExtentReportManager.logInfo("Alert displayed: " + alert.getText());
            alert.accept();
        } catch (Exception e) {
            ExtentReportManager.logFail("Alert not found within timeout: " + e.getMessage());
        }
    }

    // ** Method to Navigate to Cart **
    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)).click();
		ExtentReportManager.logInfo("Navigated to cart.");
	}
}
