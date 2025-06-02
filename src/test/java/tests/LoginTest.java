package tests;

import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import testbase.TestBase;

import java.time.Duration;
@Feature("Login Functionality")
public class LoginTest extends TestBase {
    public LoginPage loginPage;
    @Test(description = "login with valid credential")
    public void loginWithValidCredentials(){
        loginPage=new LoginPage(driver);
        loginPage.login("Admin","admin123");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
    }
    @Test(description = "login with invalid credentials",dataProvider = "invalidLoginData", dataProviderClass = utils.TestData.class)
    public void loginWithInvalidCredentials(String username, String password){
        loginPage=new LoginPage(driver);
        loginPage.login(username,password);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid credentials"));
    }

    @Test(description = "login with empty credentials",dataProvider = "emptyLoginData", dataProviderClass = utils.TestData.class)
    public void loginWithEmptyCredentials(String username, String password){
        loginPage=new LoginPage(driver);
        loginPage.login(username,password);
        Assert.assertTrue(loginPage.getRequiredErrorMessage().contains("Required"));
    }

}
