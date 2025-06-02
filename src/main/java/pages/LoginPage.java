package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

import java.util.List;

public class LoginPage extends ElementUtils {
   protected WebDriver driver;

   @FindBy(name = "username")
   private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//p[text()='Invalid credentials']")
    private WebElement invalidErrorMessage;

    @FindBy(xpath = "//span[text()='Required']")
    private List<WebElement> requiredFieldMessages;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void login(String username ,String password ){
        setText(usernameField,username);
        setText(passwordField,password);
        clickElemet(loginButton);
    }

    public String getErrorMessage() {
        return getMessage(invalidErrorMessage);
    }


    public String getRequiredErrorMessage(){
//        for (WebElement el : requiredFieldMessages) {
//            if (el.isDisplayed()) {
//                return getMessage(el);
//            }
//        }
//        return "";
        return getCommonRequiredErrorMessage(requiredFieldMessages);
    }
}
