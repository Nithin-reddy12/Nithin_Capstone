package pages;

import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private Properties prop;
	private WebDriverWait wait;

	// **Locators using Page Object Model**
	
	private By cartButton = By.xpath("//a[@class='nav-link' and contains(text(),'Cart')]");
	private By placeOrder = By.xpath("//button[contains(text(),'Place Order')]");
	private By nameField = By.id("name");
	private By countryField = By.id("country");
	private By cityField = By.id("city");
	private By cardField = By.id("card");
	private By monthField = By.id("month");
	private By yearField = By.id("year");
	private By purchaseButton = By.xpath("//button[contains(text(),'Purchase')]");
	private By successMessage = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");
	private By cartItems = By.xpath("//tbody/tr"); // Cart item rows

	public CartPage(WebDriver driver, Properties prop) {
		this.driver = driver; // Assigns the WebDriver instance to the class variable 'driver'
		this.prop = prop; // Assigns the properties object to the class variable 'prop'
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initializes a WebDriverWait with a 10-second timeout for waiting on condition
	}

	// **Open Cart Page**
	public void openCart() {
		WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(cartButton)); // Wait for the cart button to be clickable
		cartBtn.click();

		// Wait for cart page to fully load
		wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrder)); //Wait until the "Place Order" element is visible
	}

	// **Click 'Place Order'**
	public void clickPlaceOrder() {
		WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(placeOrder));// Wait for the "Place Order" button to be clickable
	    placeOrderBtn.click(); // Click the "Place Order" button

	}

	// **Fill Order Details & Submit**
	public void placeOrder() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(prop.getProperty("name"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(countryField)).sendKeys(prop.getProperty("country"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cityField)).sendKeys(prop.getProperty("city"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardField)).sendKeys(prop.getProperty("cardNumber"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(monthField)).sendKeys(prop.getProperty("month"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(yearField)).sendKeys(prop.getProperty("year"));

		wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();

	}

	// **Verify Order Success**
	public boolean verifyOrderPlaced() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
	}

	// **Check if Cart is Empty**
	public boolean isCartEmpty() {
		return driver.findElements(cartItems).isEmpty();
	}
}