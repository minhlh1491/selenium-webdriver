package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_Browser {
    WebDriver driver;

    By account = By.xpath("//div[@class='footer']//a[@title='My Account']");
    By creatAccount = By.xpath("//a[@title='Create an Account']");

    @BeforeClass
    public void setup_open(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void TC_01_VerifyUrl(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(account).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(creatAccount).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_VerifyTitle(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(account).click();
        Assert.assertEquals(driver.getTitle(),"Customer Login");
        driver.findElement(creatAccount).click();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_03_NavigateFunction(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(account).click();
        driver.findElement(creatAccount).click();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }

    @Test
    public void TC_04_GetPageSourceCode(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(account).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        driver.findElement(creatAccount).click();
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
    }

}
