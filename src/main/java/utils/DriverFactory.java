package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.edge.*;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static final String DOWNLOAD_DIR = System.getProperty("user.dir") + File.separator + "downloads";

    public static WebDriver initDriver(String browserName) {

        File downloadDir = new File(DOWNLOAD_DIR);
        if (!downloadDir.exists()) {
            boolean created = downloadDir.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create download directory at: " + DOWNLOAD_DIR);
            }
        }
        WebDriver driver;

        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications", "--incognito");
                Map<String, Object> chromePrefs = new HashMap<>();
                chromePrefs.put("download.default_directory", DOWNLOAD_DIR);
                chromePrefs.put("profile.default_content_setting_values.notifications", 2);
                chromeOptions.setExperimentalOption("prefs", chromePrefs);
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.dir", DOWNLOAD_DIR);
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("dom.webnotifications.enabled", false);
                firefoxOptions.setProfile(profile);
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--disable-notifications", "--inprivate");
                Map<String, Object> edgePrefs = new HashMap<>();
                edgePrefs.put("download.default_directory", DOWNLOAD_DIR);
                edgePrefs.put("profile.default_content_setting_values.notifications", 2);
                edgeOptions.setExperimentalOption("prefs", edgePrefs);
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        tlDriver.set(driver);
        return getDriver();
    }

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        tlDriver.remove();
    }
}
