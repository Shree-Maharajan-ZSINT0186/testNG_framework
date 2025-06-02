package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class DashboardPage extends ElementUtils {
    protected WebDriver driver;

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href=\"/web/index.php/pim/viewPimModule\"]")
    private WebElement PIMElement;

    public void findPIM(){
        clickElemet(PIMElement);
    }

}
