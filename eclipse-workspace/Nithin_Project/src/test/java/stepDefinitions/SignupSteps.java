package stepDefinitions;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SignUpPage;
import utilities.ExtentReportManager;

public class SignupSteps extends BaseClass {
    WebDriver driver;
    SignUpPage signupPage;

    @Given("the user is on homepage")
    public void the_user_is_on_homepage() throws IOException {
        ExtentReportManager.startTest("Signup Test");
        driver = getDriver(); // Use getDriver() instead of accessing directly
        signupPage = new SignUpPage(driver); // Initialize the page object
        ExtentReportManager.logInfo("User is on the homepage");
    }

    @When("the user clicks on sign up")
    public void the_user_clicks_on_sign_up() {
        signupPage.clickSignup();
        ExtentReportManager.logInfo("User clicked on Sign Up");
    }

    @When("enters a unique {string} and {string}")
    public void enters_a_unique_username_and_password(String username, String password) throws IOException, InterruptedException {
        signupPage.enterSignupDetails(username, password);
        screenshot("SignUpPage");
        ExtentReportManager.logPass("Entered signup details: " + username);
        ExtentReportManager.logPass("Entered signup details: " + password);
    }

    @When("clicks the sign up button")
    public void clicks_the_sign_up_button() {
        signupPage.clickRegister();
        ExtentReportManager.logInfo("Clicked on Register");
    }

    @Then("the user should see a success message")
    public void the_user_should_see_a_success_message() throws InterruptedException {
        String alertMessage = signupPage.handleAlert();
        if (alertMessage != null) {
            ExtentReportManager.logPass("Alert message: " + alertMessage);
        } else {
            ExtentReportManager.logFail("No alert message displayed.");
        }
    }
}
