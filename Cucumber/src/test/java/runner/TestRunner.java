package runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/main/resources/feature/"},tags= {"@21"}, glue = {"stepDef"},
plugin ={"json:target/cucumber-json-report.json", "html:target/cucumber-report-html"},strict = true)
public class TestRunner extends AbstractTestNGCucumberTests {
	 
	 
}
