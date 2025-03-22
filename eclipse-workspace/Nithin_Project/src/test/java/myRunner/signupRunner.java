package myRunner;



import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import base.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features = ".\\src\\main\\resources\\features\\signup.feature",
	glue = { "stepDefinations","hooks"},
	plugin = { "pretty","html:target/cucumber-reports.html"}, 
	tags = "@signup", 
	monochrome = false)
public class signupRunner extends AbstractTestNGCucumberTests {
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws IOException {
		BaseClass.invokeBrowser(browser); // Initialize browser
	}
}

