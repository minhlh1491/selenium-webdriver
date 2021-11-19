package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Topic_Element {
    WebDriver driver;

    @BeforeClass
    public void setup_open(){
        String address ="";
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        System.out.println(address);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
