package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='user_email']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='user_password']")
    private WebElement password;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginSubmit;

    @FindBy(xpath = "//a[@class='link-below-button']")
    private WebElement forgotPassword;

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getLoginSubmit() {
        return loginSubmit;
    }

    public WebElement getForgotPassword() {
        return forgotPassword;
    }
}
