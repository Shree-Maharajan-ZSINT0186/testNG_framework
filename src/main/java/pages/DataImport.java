package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ElementUtils;

public class DataImport extends ElementUtils {
    public WebDriver driver;

    public DataImport(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@class='download-link']")
    public WebElement download_link;


//    public By fileBrowse = By.cssSelector("input.oxd-file-input");

    @FindBy(css = "input.oxd-file-input")
    public WebElement fileBrowse;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//p[text()='Import Details']")
    public WebElement status;

    @FindBy(xpath = "//button[text()=' Ok ']")
    public WebElement close;


    public void downloadFile(){
        clickElemet(download_link);
    }

    public String getStatus(){
       return getMessage(status);
    }

    public void fileUpload(){
        fileBrowse.sendKeys("D:\\testNG_framework\\src\\test\\java\\Resources\\importData.csv");
        clickElemet(submitButton);

    }


    public void closeModel(){
    clickElemet(close);
    }
}
