package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ModelsPage;
import testbase.TestBase;

public class ModelsTest extends TestBase {
    ModelsPage modelsPage;
    @BeforeMethod
    public void ModelsSetUp(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        modelsPage=new ModelsPage(driver);
    }

    @Test(description = "javascript alert")
    public void javaScriptAlert(){
        modelsPage.openAlert();
    }
    @Test(description = "send keys on alert")
    public void sendKeysOnAlert(){
    modelsPage.sendKeysOnAlert();
    }
}
