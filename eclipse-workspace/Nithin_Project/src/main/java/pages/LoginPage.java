package pages;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;

	// @FindBy Annotation to find the elements
	@FindBy(id = "login2")
	WebElement loginButton;

	@FindBy(id = "loginusername")
	WebElement usernameField;

	@FindBy(id = "loginpassword")
	WebElement passwordField;

	@FindBy(xpath = "//button[text()='Log in']")
	WebElement submitButton;

	@FindBy(xpath = "//a[contains(text(),'Welcome')]") // Updated XPath
	WebElement welcomeMessage;

	// Constructor with page factory
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for elements
	}

	public void login(String username, String password) throws InterruptedException, IOException {
		wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
		Thread.sleep(2000); // Wait for login modal to appear
		wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
		passwordField.sendKeys(password);
		submitButton.click();
	}

	public boolean isUserLoggedIn() {
		try {
			return wait.until(ExpectedConditions.visibilityOf(welcomeMessage)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}