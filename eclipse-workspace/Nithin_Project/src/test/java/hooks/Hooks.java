package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ExtentReportManager;

public class Hooks {

	@Before
	public void setUp(Scenario scenario) {
		String scenarioName = scenario.getName(); // Get scenario name dynamically
		ExtentReportManager.startTest(scenarioName);
	}

	@After
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			ExtentReportManager.logFail("Scenario Failed: " + scenario.getName());
		} else {
			ExtentReportManager.logPass("Scenario Passed: " + scenario.getName());
		}
		ExtentReportManager.endTest(); // Flush the report
	}
}
