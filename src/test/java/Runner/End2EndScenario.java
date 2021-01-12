package Runner;


        /*
            Create one runner class
                run  create edit delete country features
                Fees feature scenario outline

         Runner is helping us to run multiple feature files and also we can choose which scenario to run in the feature file.

         */

import Driver.DriverClass;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;

import java.io.File;


@CucumberOptions(

        plugin = {
                "html:target/cucumber-Html-Report",

        },
        tags = "@Smoke",
        features = {"src/test/java/FeatureFiles"},
        glue = {"StepDefinition"}


)

public class End2EndScenario extends AbstractTestNGCucumberTests {


        @AfterClass
        public static void afterClass(){

//                Location of the xml file
                Reporter.loadXMLConfig(new File("src/test/java/FeatureFiles/extentReport.xml"));
                Reporter.setSystemInfo("User Name" , "Ali Koklu");
                Reporter.setSystemInfo("Application Name", "Basqar");
                Reporter.setSystemInfo("Operating System Type" , System.getProperty("os.name"));
                Reporter.setSystemInfo("Environment", "QA");
                Reporter.setTestRunnerOutput("Test execution Cucumber report");
        }
}

