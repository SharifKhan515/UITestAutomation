package resources;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public WebDriver driver;
    public String propertyPath = System.getProperty("user.dir") + "\\data.properties";
    public String screenshotDirectory = System.getProperty("user.dir") + "\\reports\\screenshot";

    public WebDriver initializeDriver() throws IOException {
        String browser = getBrowser();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("headless");

        switch (browser) {
            case "CHROME":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                //WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver();
                break;
            case "CHROMEHEADLESS":
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "FIREFOXHEADLESS":
                WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "IE":
                WebDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public String getBrowser() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(propertyPath);
        prop.load(fis);
        if (System.getProperty("browser") == null) {
            return prop.getProperty("browser").toUpperCase();
        } else {
            return System.getProperty("browser").toUpperCase();
        }
    }

    public String getHomeUrl() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(propertyPath);
        prop.load(fis);
        return prop.getProperty("homeUrl");
    }

    public String getLoginUrl() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(propertyPath);
        prop.load(fis);
        return prop.getProperty("loginUrl");
    }

    public String getScreenshot(String methodName, WebDriver driver) throws IOException {

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String filename = screenshotDirectory + "\\" + methodName + ".png";
        FileUtils.copyFile(source, new File(filename));
        return filename;
    }
}
