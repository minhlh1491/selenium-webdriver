package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Topic_09_Button_Radio_Checkbox_Alert {
    WebDriver driver;
    String emailtxt = "minhluu1491@gmail.com";
    String pwtxt = "123456@X";
    String loginTab="//ul[@id='popup-login-tab_list']//a[contains(text(),'Đăng nhập')]";
    String btnLogin = "//button[@class='fhs-btn-login']";
    String messTeleEmail="//input[@id='login_username']/ancestor::div/following-sibling::div[@class='fhs-input-alert']";
    String messPassword = "//input[@id='login_password']/ancestor::div/following-sibling::div[@class='fhs-input-alert']";
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void setup_open() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
    }

    @AfterClass
    public void tearDown() {

//        driver.quit();
    }

    @Test
    public void TC_01_Button(){
        driver.get("https://www.fahasa.com/customer/account/create");

        driver.findElement(By.xpath(loginTab)).click();

        Assert.assertTrue(driver.findElement(By.xpath(btnLogin)).isDisplayed());
        sleepSecond(3);

        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys(emailtxt);
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys(pwtxt);

        Assert.assertTrue(driver.findElement(By.xpath(btnLogin)).isEnabled());

        driver.navigate().refresh();
        driver.findElement(By.xpath(loginTab)).click();

        jsExecutor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath(btnLogin)));


        jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",driver.findElement(By.xpath(btnLogin)));
        sleepSecond(2);

        driver.findElement(By.xpath(btnLogin)).click();
        sleepSecond(2);

        Assert.assertEquals(driver.findElement(By.xpath(messTeleEmail)).getText(),"Thông tin này không thể để trống");
        Assert.assertEquals(driver.findElement(By.xpath(messPassword)).getText(),"Thông tin này không thể để trống");



    }



    public void sleepSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
