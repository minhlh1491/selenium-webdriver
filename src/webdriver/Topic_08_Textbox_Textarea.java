package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Textbox_Textarea {
    WebDriver driver;
    String loginPageUrl, userId, userPassword;
    String customerName, customerID, gender, dob, addressInput,addressOutput,addressEdit, city, state, pin, telephone, email, password;
    String editAddressInput,editAddressOutput, editCity, editState, editPin, editTelephone, editEmail;
    JavascriptExecutor javascriptExecutor;

    @BeforeClass
    public void setup_open() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        javascriptExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.guru99.com/v4/");
        loginPageUrl = "";
        email = "minhlh" + getRandomNumber() + "@gmail.com";

        customerName = "Minh";
        gender = "female";
        dob = "1991-04-01";
        addressInput = "KP 3\nThoi An";
        addressOutput = "KP 3 Thoi An";
        city = "Ho Chi Minh";
        state = "ThoiAn";
        pin = "123456";
        telephone = "0987654321";
        password = "123456";

        editAddressInput = "KP 5\nThoi An 10";
        editAddressOutput = "KP 5 Thoi An 10";
        editCity = "Da nang";
        editState = "TanChanhHiep";
        editPin = "456789";
        editTelephone = "0981234567";
        editEmail= "minhlh" + getRandomNumber() + "@gmail.com";

    }

    @Test
    public void TC_01_Register() {
        loginPageUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        userPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login() {
        driver.get(loginPageUrl);
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(userPassword);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
        Assert.assertEquals(driver.findElement(By.cssSelector("tr.heading3>td")).getText(), "Manger Id : " + userId);
    }

    @Test
    public void TC_03_Create_Customer() {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        sleepSecond(1);
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(customerName);

        if (gender.equals("male")){
            driver.findElement(By.xpath("//input[@value='m']")).click();
        }else driver.findElement(By.xpath("//input[@value='f']")).click();

        WebElement dobTextbox = driver.findElement(By.xpath("//input[@name='dob']"));
        javascriptExecutor.executeScript("arguments[0].removeAttribute('type')",dobTextbox);
        dobTextbox.sendKeys(dob);
        sleepSecond(3);

        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(addressInput);
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(telephone);
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='sub']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(),"Customer Registered Successfully!!!");

        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),telephone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),email);
        customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
    }

    @Test
    public void TC_04_EditCustomer(){
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
        driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"),customerName);
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(),addressInput);

        driver.findElement(By.xpath("//textarea[@name='addr']")).clear();
        driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(editAddressInput);
        driver.findElement(By.xpath("//input[@name='city']")).clear();
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(editCity);
        driver.findElement(By.xpath("//input[@name='state']")).clear();
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(editState);
        driver.findElement(By.xpath("//input[@name='pinno']")).clear();
        driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(editPin);
        driver.findElement(By.xpath("//input[@name='telephoneno']")).clear();
        driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(editTelephone);
        driver.findElement(By.xpath("//input[@name='emailid']")).clear();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(editEmail);
        driver.findElement(By.xpath("//input[@name='sub']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("p.heading3")).getText(),"Customer details updated Successfully!!!");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddressOutput);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editCity);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editState);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),editPin);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editTelephone);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail);

    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(99999);
    }

    public void sleepSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
