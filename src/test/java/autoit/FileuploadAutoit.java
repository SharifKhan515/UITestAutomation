package autoit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.table.TableRowSorter;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class FileuploadAutoit {
    public static void main(String[] args) throws InterruptedException, IOException {

        String downloadFolder = System.getProperty("user.dir") + "\\Download";
       // String downloadFolder = System.getProperty("user.dir");
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFolder);

        System.setProperty("webdriver.chrome.driver", "E:\\Automation\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://smallpdf.com/word-to-pdf");
        driver.findElement(By.xpath("//span[contains(text(),'Choose Files')]")).click();
        Thread.sleep(3000);
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\fileupload.exe";
        Runtime.getRuntime().exec(path);
        WebDriverWait wait = new WebDriverWait(driver, 120);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Download')]")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Download')]")));
        driver.findElement(By.xpath("//span[contains(text(),'Download')]")).click();
        Thread.sleep(5000);
        driver.close();

        String downloadFilePath = downloadFolder + "\\Md. Sharif Khan cv -QA-converted.pdf";
        File file = new File(downloadFilePath);
        if (file.exists()) {
            Assert.assertTrue(true);
            System.out.println("file exist");
            if(file.delete()){
                System.out.println("Deleted the file");
                Assert.assertTrue(true);
            }
        } else {
            System.out.println("File does not exist ");
        }

    }
}
