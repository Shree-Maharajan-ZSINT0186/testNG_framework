package tests;

import io.qameta.allure.Feature;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import testbase.TestBase;
import utils.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;
@Feature("Employee details Functionality")
public class EmployeeTest extends TestBase {
    AddEmployeePage addEmployeePage;
    DataImport dataImport;
    @BeforeMethod
    public void addEmployeeSetUp(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login("Admin","admin123");
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.findPIM();

    }

    @Test(description = "add valid employee details without login credentials",dataProvider = "employeeData",dataProviderClass = utils.TestData.class)
    public void addValidEmployeeDetails(Map<String, String> employee){
        PIMPage pimPage=new PIMPage(driver);
        pimPage.findMenuAddEmployee();
        addEmployeePage = new AddEmployeePage(driver);
        String firstName = employee.get("firstname");
        String middleName = employee.get("middlename");
        String lastName = employee.get("lastname");
        addEmployeePage.addEmployee(firstName,middleName,lastName);
        Assert.assertTrue(addEmployeePage.getSuccessMessage().contains("Success"));
    }
    @Test(description = "add Empty employee details without login credentials",dataProvider = "emptyEmployeeData",dataProviderClass = utils.TestData.class)
    public void addEmptyEmployeeDetails(Map<String, String> employee){
        PIMPage pimPage=new PIMPage(driver);
        pimPage.findMenuAddEmployee();
        addEmployeePage = new AddEmployeePage(driver);
        String firstName = employee.get("firstname");
        String middleName = employee.get("middlename");
        String lastName = employee.get("lastname");
        addEmployeePage.addEmployee(firstName,middleName,lastName);
        Assert.assertTrue(addEmployeePage.getRequiredErrorMessage().contains("Required"));
    }
    @Test(description = "add valid employee details with login credentials",dataProvider = "employeeLoginData",dataProviderClass = utils.TestData.class)
    public void addValidEmployeeWithLoginCredential(Map<String, String> employee){
        PIMPage pimPage=new PIMPage(driver);
        pimPage.findMenuAddEmployee();
        addEmployeePage = new AddEmployeePage(driver);
        String firstName = employee.get("firstname");
        String middleName = employee.get("middlename");
        String lastName = employee.get("lastname");
        String userName=employee.get("username");
        String password=employee.get("password");
        String ConfirmPassword=employee.get("confirmPassword");

        addEmployeePage.addEmployeeWithLogin(firstName,middleName,lastName,userName,password,ConfirmPassword);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/viewPersonalDetails/empNumber"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/viewPersonalDetails/empNumber"));
    }

    @Test(dataProvider = "invalidLoginCredentials", dataProviderClass = utils.TestData.class)
    public void testInvalidLoginScenarios(Map<String, String> data) {
        PIMPage pimPage=new PIMPage(driver);
        pimPage.findMenuAddEmployee();
        addEmployeePage = new AddEmployeePage(driver);
        addEmployeePage.addEmployeeWithLogin(
                data.get("firstname"),
                data.get("middlename"),
                data.get("lastname"),
                data.get("username"),
                data.get("password"),
                data.get("confirmPassword")
        );

        String actualError = addEmployeePage.getValidationErrorMessage();
        Assert.assertTrue(actualError.contains(data.get("expectedError")));
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        if (dirContents != null) {
            for (File file : dirContents) {
                if (file.getName().equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Test(description = "download the file")
    public void DownloadFile() throws IOException {
        String expectedFileName = "importData.csv";
        PIMPage pimPage=new PIMPage(driver);
        pimPage.SelectDropDown();
        dataImport=new DataImport(driver);
        dataImport.downloadFile();
        new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean fileDownloaded = isFileDownloaded(DriverFactory.DOWNLOAD_DIR, expectedFileName);
        Assert.assertTrue(fileDownloaded, "File was not downloaded successfully.");
    }

    @Test(description = "file upload")
    public void FileUpload(){
        PIMPage pimPage=new PIMPage(driver);
        pimPage.SelectDropDown();
        dataImport=new DataImport(driver);
        dataImport.fileUpload();
        Assert.assertTrue(dataImport.getStatus().contains("Import Details"));
        dataImport.closeModel();
    }

}
