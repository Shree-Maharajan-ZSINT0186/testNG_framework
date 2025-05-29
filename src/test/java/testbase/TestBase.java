package testbase;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.DriverFactory;

public class TestBase {
    protected WebDriver driver;
    @Parameters({"browser"})
    @BeforeMethod
    public void initDriver(@Optional("chrome") String browser){
        driver= DriverFactory.initDriver(browser);
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
