package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

import java.util.List;

public class AddEmployeePage extends ElementUtils {

    public WebDriver driver;

    public AddEmployeePage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "middleName")
    private WebElement middleNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

//    @FindBy(css = ".oxd-toast--success .oxd-toast-content--success .oxd-toast-content-text")
//    private WebElement successMessageElement;

    @FindBy(xpath = "//span[text()='Required']")
    private List<WebElement> requiredFieldMessages;

    @FindBy(xpath = "//input[@type='checkbox']/following::span[1]")
    private WebElement checkbox;

    @FindBy(xpath = "//label[text()='Password']/following::input[1]")
    private  WebElement passwordField;

    @FindBy(xpath = "//label[text()='Username']/following::input[1]")
    private WebElement usernameField;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[1]")
    private  WebElement confirmPasswordField;

    @FindBy(xpath = "//span[text()='Should be at least 5 characters']")
    private WebElement usernameLengthError;

    @FindBy(xpath = "//span[text()='Should have at least 7 characters']")
    private WebElement passwordLengthError;

    @FindBy(xpath = "//span[text()='Your password must contain minimum 1 lower-case letter']")
    private WebElement lowercaseRequirementError;

    @FindBy(xpath = "//span[text()='Passwords do not match']")
    private WebElement passwordMismatchError;

    public void addEmployee(String firstname,String middlename,String lastname){
        setText(firstNameField,firstname);
        setText(middleNameField,middlename);
        setText(lastNameField,lastname);
        clickElemet(saveButton);
    }

    public String getSuccessMessage(){
        return getMessage(successMessageElement);
    }

    public String getRequiredErrorMessage(){
       return getCommonRequiredErrorMessage(requiredFieldMessages);
    }

  public void  addEmployeeWithLogin(String firstName,String middleName,String lastName,String userName,String password,String confirmPassword){
      setText(firstNameField,firstName);
      setText(middleNameField,middleName);
      setText(lastNameField,lastName);
      checkbox.click();
      setText(usernameField,userName);
      setText(passwordField,password);
      setText(confirmPasswordField,confirmPassword);
      clickElemet(saveButton);
  }

    public String getValidationErrorMessage() {
        StringBuilder errorMessages = new StringBuilder();

        try {
            if (usernameLengthError.isDisplayed()) {
                errorMessages.append(usernameLengthError.getText()).append(" | ");
            }
        } catch (Exception ignored) {}

        try {
            if (passwordLengthError.isDisplayed()) {
                errorMessages.append(passwordLengthError.getText()).append(" | ");
            }
        } catch (Exception ignored) {}

        try {
            if (lowercaseRequirementError.isDisplayed()) {
                errorMessages.append(lowercaseRequirementError.getText()).append(" | ");
            }
        } catch (Exception ignored) {}

        try {
            if (passwordMismatchError.isDisplayed()) {
                errorMessages.append(passwordMismatchError.getText()).append(" | ");
            }
        } catch (Exception ignored) {}

        String result = errorMessages.toString().trim();
        if (result.endsWith("|")) {
            result = result.substring(0, result.length() - 1).trim();
        }

        return result;
    }

}
