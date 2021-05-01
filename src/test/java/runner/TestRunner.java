package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefs",
        plugin = {"pretty","html:target/Reqres_report.html"},
        monochrome = true,
        publish = true
)

public class TestRunner {
}
