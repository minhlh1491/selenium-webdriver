package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_HTML_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void setup_open() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_rode(){
        driver.get("https://www.rode.com/wheretobuy");

        select = new Select(driver.findElement(By.xpath("//select[@name='where_country']")));
        Assert.assertFalse(select.isMultiple());

        select.selectByVisibleText("Vietnam");
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");

        driver.findElement(By.id("search_loc_submit")).click();
        sleepSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result_count>span")).getText(),"31");

        List<WebElement> store_names = driver.findElements(By.xpath("//div[@class='result_item']//div[@class='store_name']"));

        for (WebElement webElement:store_names){
            System.out.println(webElement.getText());
        }
    }

    @Test
    public void TC_02_nopcommerce(){
        driver.get("https://demo.nopcommerce.com/register");

        String gender = "male";
        String firstName="minh";
        String lastName="luu";
        String email=firstName + getRandomNumber() + lastName + "@gmail.com";
        String password="Minh@1234";
        String day="1";
        String month="April";
        String year="1991";

        driver.findElement(By.className("ico-register")).click();

        if (gender.equals("male")){
            driver.findElement(By.xpath("//input[@id='gender-male']")).click();
        }else driver.findElement(By.xpath("//input[@id='gender-female']")).click();

        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        Select dayDropdown = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        dayDropdown.selectByVisibleText(day);

        Select monthDropdown = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
        monthDropdown.selectByVisibleText(month);

        Select yearDropdown = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
        yearDropdown.selectByVisibleText(year);

        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");

        driver.findElement(By.className("ico-account")).click();

    }


    @AfterClass
    public void tearDown() {
        driver.quit();
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
