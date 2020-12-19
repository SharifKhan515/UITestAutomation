package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import resources.Base;

import java.io.IOException;

public class LoginPageTest extends Base {

    public static Logger log = LogManager.getLogger(LoginPageTest.class.getName());

    WebDriver driver;
    String homeURL;

    @BeforeTest
    public void setDriver() throws IOException {
        driver = initializeDriver();
        log.info("driver initialize");
        homeURL = getHomeUrl();
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        log.info("driver closed");
    }

    @Test
    public void login(){
        driver.get(homeURL);
        HomePage homePage = new HomePage(driver);
        log.info("visited homepage");
        // homePage.getLogin().click();
       // LoginPage loginPage = new LoginPage(driver);
        LoginPage loginPage = homePage.getLogin();
        loginPage.getUsername().sendKeys("Sharifkhan@gmail.com");
        loginPage.getPassword().sendKeys("Password");
        loginPage.getLoginSubmit().click();
    }

}
