package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutUsPage {
	WebDriver driver;

	
	//***Using Page Factory**
	
	// @FindBy Annotation to find the elements
	@FindBy(xpath = "//a[contains(text(),'About us')]")
	WebElement aboutUsBtn;

	// Constructor with page factory
	public AboutUsPage(WebDriver driver) {
		this.driver = driver; // Assigns the WebDriver instance to the class variable 'driver'
		PageFactory.initElements(driver, this); // Initializes all elements annotated with @FindBy in this class
	}

	// Open About Us section
	public void openAboutUs() throws IOException, InterruptedException {
		aboutUsBtn.click();
		Thread.sleep(2000);
	}
}