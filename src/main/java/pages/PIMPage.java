package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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

    public void findMenuAddEmployee(){
        clickElemet(addEmployeeButton);
    }

}
