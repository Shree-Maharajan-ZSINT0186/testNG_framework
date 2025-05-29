package utils;

import org.testng.annotations.DataProvider;

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

}
