package autoit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
//bypass winodow popup auth using cred in url
public class AuthenticationWindow {
    public static void main(String[] args){

        System.setProperty("webdriver.chrome.driver", "E:\\Automation\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://admin:admin@the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Basic Auth')]")).click();
        String message = driver.findElement(By.xpath("//p")).getText();

        System.out.println(message);




    }
}
