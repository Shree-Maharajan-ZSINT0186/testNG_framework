package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import testbase.TestBase;

import java.util.Map;

public class EmployeeTest extends TestBase {
    AddEmployeePage addEmployeePage;
    @BeforeMethod
    public void addEmployeeSetUp(){
        LoginPage loginPage=new LoginPage(driver);
        loginPage.login("Admin","admin123");
        DashboardPage dashboardPage=new DashboardPage(driver);
        dashboardPage.findPIM();
        PIMPage pimPage=new PIMPage(driver);
        pimPage.findMenuAddEmployee();
        addEmployeePage = new AddEmployeePage(driver);

    }

    @Test(description = "add valid employee details without login credentials",dataProvider = "employeeData",dataProviderClass = utils.TestData.class)
    public void addValidEmployeeDetails(Map<String, String> employee){
        String firstName = employee.get("firstname");
        String middleName = employee.get("middlename");
        String lastName = employee.get("lastname");
        addEmployeePage.addEmployee(firstName,middleName,lastName);
        Assert.assertTrue(addEmployeePage.getSuccessMessage().contains("Success"));
    }
    @Test(description = "add Empty employee details without login credentials",dataProvider = "emptyEmployeeData",dataProviderClass = utils.TestData.class)
    public void addEmptyEmployeeDetails(Map<String, String> employee){
        String firstName = employee.get("firstname");
        String middleName = employee.get("middlename");
        String lastName = employee.get("lastname");
        addEmployeePage.addEmployee(firstName,middleName,lastName);
        Assert.assertTrue(addEmployeePage.getRequiredErrorMessage().contains("Required"));
    }
    @Test(description = "add valid employee details with login credentials",dataProvider = "employeeLoginData",dataProviderClass = utils.TestData.class)
    public void addValidEmployeeWithLoginCredential(Map<String, String> employee){
        String firstName = employee.get("firstname");
        String middleName = employee.get("middlename");
        String lastName = employee.get("lastname");
        String userName=employee.get("username");
        String password=employee.get("password");
        String ConfirmPassword=employee.get("confirmPassword");

        addEmployeePage.addEmployeeWithLogin(firstName,middleName,lastName,userName,password,ConfirmPassword);
        Assert.assertTrue(addEmployeePage.getSuccessMessage().contains("Success"));
    }

    @Test(dataProvider = "invalidLoginCredentials", dataProviderClass = utils.TestData.class)
    public void testInvalidLoginScenarios(Map<String, String> data) {
        addEmployeePage.addEmployeeWithLogin(
                data.get("firstname"),
                data.get("middlename"),
                data.get("lastname"),
                data.get("username"),
                data.get("password"),
                data.get("confirmPassword")
        );

        String actualError = addEmployeePage.getValidationErrorMessage();
        System.out.println(actualError);
        Assert.assertTrue(actualError.contains(data.get("expectedError")));
    }


}
