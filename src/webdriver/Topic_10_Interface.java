package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_10_Interface {
    WebDriver driver;
    WebDriverWait explicitwait;
    JavascriptExecutor jsExecutor;
    Actions action;

    @BeforeClass
    public void setup_open() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        jsExecutor = (JavascriptExecutor) driver;
        action = new Actions (driver);

        explicitwait = new WebDriverWait(driver,20);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
//        driver.quit();
    }


    public void TC_01_Hover_elements_tooltip(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement txtboxYourAge = driver.findElement(By.xpath("//input[@id='age']"));
        action.moveToElement(txtboxYourAge).perform();

        WebElement tooltip = driver.findElement(By.xpath("//div[@class='ui-tooltip-content']"));
        Assert.assertTrue(tooltip.isDisplayed());
    }

    public void TC_02_Hover_elements(){
        driver.get("https://www.myntra.com/");

        WebElement eleKIDs = driver.findElement(By.xpath("//a[@data-group='kids']"));
        WebElement eleTOPS = driver.findElement(By.xpath("//a[@data-group='kids']"));

        action.moveToElement(eleKIDs).perform();
        eleTOPS.click();



    }

}
