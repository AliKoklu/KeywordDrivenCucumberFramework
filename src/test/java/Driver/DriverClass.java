package Driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverClass {


    public WebDriver driver;


    public WebDriver init_driver(String browserName){

        if(browserName.equals("chrome")){
            ChromeDriverManager.chromedriver().setup();

            driver = new ChromeDriver();

        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public void QuitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null; // if we dont type this line next scenario will fail error is NoSuchSessionException
        }
    }
}
