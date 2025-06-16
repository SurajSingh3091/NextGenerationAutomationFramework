package TestRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/Feature/OrangeHRM/"},
        glue = {"StepDefinition"},
        tags = ("@AddEmployee"),
        monochrome = true,
        dryRun = true ,
        plugin = {"pretty"}

)

public class TestRunner {
   
}
