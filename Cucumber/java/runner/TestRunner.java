package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/Cucumber/"}, glue = {"src/test/resources/stepDefinition/"},plugin= {"json:target/cucumber-json-report.json,html:target/cucumber-report-html"})

public class TestRunner {

}
