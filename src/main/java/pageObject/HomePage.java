package pageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public WebDriver driver;
    public static Logger log = LogManager.getLogger(HomePage.class.getName());

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='login-btn'][2]/a")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='pull-left']")
    private WebElement featureTitle;

    @FindBy(xpath = "//ul[contains(@class,'navigation clearfix')]")
    private WebElement navbar;


    public LoginPage getLogin() {

        loginButton.click();

        return new LoginPage(driver);
    }

    public WebElement getFeatureTitle() {

        return featureTitle;
    }

    public WebElement getNavbar() {

        return navbar;
    }
}
