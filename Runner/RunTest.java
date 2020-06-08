package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features="src/test/java/Feature/CarWale.feature"
,glue="Steps"
,monochrome=true)

public class RunTest extends AbstractTestNGCucumberTests {

}
