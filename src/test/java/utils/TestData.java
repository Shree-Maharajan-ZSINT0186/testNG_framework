package utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestData {
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginDataProvider() {
        return new Object[][] {
                {"invalidUser", "wrongPass"},
                {"invalidUser", "admin123"},
                {"Admin", "wrongPass"},
        };
    }

    @DataProvider(name = "emptyLoginData")
    public Object[][] emptyLoginDataProvider() {
        return new Object[][] {
                {"","admin123"},
                {"invalidUser", ""},
                {"", ""}
        };
    }

    @DataProvider(name = "employeeData")
    public Object[][] getEmployeeData() throws IOException, InvalidFormatException {
        List<Map<String, String>> data = ExcelUtils.getExcelData("src/test/java/Resources/random_names.xlsx");

        // Convert List<Map<String, String>> to Object[][]
        Object[][] employeeData = new Object[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            // Convert each row (Map) to an Object array
            employeeData[i] = new Object[] { row };
        }

        return employeeData;
    }

    @DataProvider(name = "emptyEmployeeData")
    public Object[][] getEmptyEmployeeData() throws IOException, InvalidFormatException {
        List<Map<String, String>> data = ExcelUtils.getExcelData("src/test/java/Resources/emptyEmployeeData.xlsx");

        // Convert List<Map<String, String>> to Object[][]
        Object[][] employeeData = new Object[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            // Convert each row (Map) to an Object array
            employeeData[i] = new Object[] { row };
        }

        return employeeData;
    }
    @DataProvider(name = "employeeLoginData")
    public Object[][] getEmployeeLoginData() throws IOException, InvalidFormatException {
        List<Map<String, String>> data = ExcelUtils.getExcelData("src/test/java/Resources/EmployeeLoginData.xlsx");

        // Convert List<Map<String, String>> to Object[][]
        Object[][] employeeData = new Object[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            // Convert each row (Map) to an Object array
            employeeData[i] = new Object[] { row };
        }

        return employeeData;
    }

    @DataProvider(name = "invalidLoginCredentials")
    public Object[][] getEmployeeInvalidLoginData() throws IOException, InvalidFormatException {
        List<Map<String, String>> data = ExcelUtils.getExcelData("src/test/java/Resources/invaliLoginCredentials.xlsx");

        // Convert List<Map<String, String>> to Object[][]
        Object[][] employeeData = new Object[data.size()][];

        for (int i = 0; i < data.size(); i++) {
            Map<String, String> row = data.get(i);
            // Convert each row (Map) to an Object array
            employeeData[i] = new Object[] { row };
        }

        return employeeData;
    }

}
