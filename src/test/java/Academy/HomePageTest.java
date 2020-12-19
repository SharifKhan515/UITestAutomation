package Academy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObject.HomePage;
import resources.Base;

import java.io.IOException;

public class HomePageTest extends Base {
    public WebDriver driver;
    public String homeURL;
    public static Logger log = LogManager.getLogger(HomePageTest.class.getName());
    public HomePage homePage;

    @BeforeTest
    public void setDriver() throws IOException {
        driver = initializeDriver();
        homeURL = getHomeUrl();
        homePage = new HomePage(driver);
        //driver.get(homeURL);
    }

    @AfterTest
    public void teardown(){
        driver.close();
    }

    @Test
    public void basePageNavigation() {
        driver.get(homeURL);
        System.out.println(driver.getTitle());
    }

    @Test
    public void loginButtonCheck() {
        driver.get(homeURL);
        homePage.getLogin();
        String title = driver.getTitle();
        System.out.println(title);
    }

    @Test
    public void titleCheck() {
       driver.get(homeURL);
        //HomePage homePage = new HomePage(driver);
        String title = homePage.getFeatureTitle().getText();
        System.out.println(title);
        Assert.assertEquals(title, "Featured Courses123");
    }

    @Test
    public void navbarCheck() {
       driver.get(homeURL);
        new HomePage(driver);
        Assert.assertTrue(homePage.getNavbar().isDisplayed());
        log.info("Navigation bar initialized");
    }
}
