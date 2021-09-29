package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_Xpath_Css_Part_I {
    WebDriver driver;

    @BeforeClass
    public void setup_open(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Register_Empty_Data(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void TC_02_Register_Invalid_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("test123@123@");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("test123@123@");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("test123@123@");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("test123@123@");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0935210823");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_03_Register_Incorrect_Confirm_Email(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.net");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("test123@123@");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("test123@123@");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0935210823");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Register_Password_Less_6char(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("1234");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0935210823");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Register_Incorrect_Confirm_Password(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123457");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0935210823");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Register_Invalid_Phone_Number_1(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("e");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập con số");

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("093521082345");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("John Wick");
        driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
        driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0456789784");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void sleepInSecond(long second){
        try {
            Thread.sleep(second * 100);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }



}
