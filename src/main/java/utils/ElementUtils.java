package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementUtils {
    private WebDriver driver;
    private WebDriverWait wait;
    public ElementUtils(WebDriver driver) {
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    }
    public void setText(WebElement usernameField , String username){
        usernameField.sendKeys(username);
    }
    public void clickElemet(WebElement buttonField){
        wait.until(ExpectedConditions.visibilityOf(buttonField)).click();
    }
    public  String getMessage(WebElement errorMessage){
        return  wait.until((ExpectedConditions.visibilityOf(errorMessage))).getText();
    }

    public String getCommonRequiredErrorMessage(List<WebElement> requiredFieldMessages){
        for (WebElement el : requiredFieldMessages) {
            if (el.isDisplayed()) {
                return getMessage(el);
            }
        }
        return "";
    }
}
