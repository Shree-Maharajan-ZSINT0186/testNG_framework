package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class ModelsPage extends ElementUtils {
    protected WebDriver driver;

    public ModelsPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@onclick=\"jsAlert()\"]")
    private WebElement openAlert;

    @FindBy(xpath = "//button[@onclick=\"jsPrompt()\"]")
    private WebElement sendValues;

    public void openAlert(){
        clickElemet(openAlert);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void sendKeysOnAlert(){
        clickElemet(sendValues);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("im shreenehaa");
        alert.accept();
    }

}
