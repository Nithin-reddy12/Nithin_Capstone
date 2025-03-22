package pages;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {

	WebDriver driver;
	WebDriverWait wait;

	// @FindBy Annotation to find the elements
	@FindBy(id = "signin2")
	WebElement signupButton;

	@FindBy(id = "sign-username")
	WebElement usernameField;

	@FindBy(id = "sign-password")
	WebElement passwordField;

	@FindBy(xpath = "//button[text()='Sign up']")
	WebElement registerButton;

	// Constructor with page factory
	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		if (this.driver == null) {
			throw new IllegalStateException("WebDriver instance is null");
		}
		PageFactory.initElements(this.driver, this);
	}

	public void clickSignup() {
		signupButton.click();
	}

	public void enterSignupDetails(String username, String password) throws IOException, InterruptedException {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		Thread.sleep(2000);
	}

	public void clickRegister() {
		registerButton.click();
	}

	// Method to handle alerts
	public String handleAlert() {
		try {
			// Wait for the alert to be present
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert(); // Switch to the alert
			String alertMessage = alert.getText(); // Get alert message
			alert.accept(); // Accept the alert
			return alertMessage; // Return the message
		} catch (Exception e) {
			return null; // Return null if no alert is found
		}
	}
}