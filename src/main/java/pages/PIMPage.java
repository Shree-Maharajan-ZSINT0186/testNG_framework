package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.ElementUtils;

public class PIMPage extends ElementUtils {
    public  WebDriver driver;
    public PIMPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[text()='Add Employee']")
    private WebElement addEmployeeButton;

    @FindBy(xpath = "//li[.//span[text()[normalize-space()='Configuration']] and contains(@class,'--parent')]")
    private WebElement configDropdown;

    @FindBy(xpath = "//a[text()='Data Import']")
    private WebElement dataImportElement;

    public void findMenuAddEmployee(){
        clickElemet(addEmployeeButton);
    }

   public void  SelectDropDown(){
       clickElemet(configDropdown);
       clickElemet(dataImportElement);

   }
}
