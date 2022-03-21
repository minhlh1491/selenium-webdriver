package webdriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_CustomDropdown {
    WebDriver driver;

    WebDriverWait explicitWait;
    List<WebElement> childItems;

    @BeforeClass
    public void setup_open() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,30);

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    //@Test
    public void TC_01_Jquery(){
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // click để hiển thị tất cả các items
        selectItemsDropdown("//span[@id='number-button']","//ul[@id='number-menu']/li/div","5");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"5");


        selectItemsDropdown("//span[@id='number-button']","//ul[@id='number-menu']/li/div","12");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"12");


        selectItemsDropdown("//span[@id='number-button']","//ul[@id='number-menu']/li/div","13");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"13");


        selectItemsDropdown("//span[@id='number-button']","//ul[@id='number-menu']/li/div","4");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),"4");




    }

    //@Test
    public void TC_02_ReactJS(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemsDropdown("//div[@role='listbox']","//span[@class='text']","Christian");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(),"Christian");
    }

    //@Test
    public void TC_03_VueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        selectItemsDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");

        selectItemsDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Second Option");

        selectItemsDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Third Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Third Option");
    }

    @Test
    public void TC_04_Angular(){
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        sleepSecond(2);

        selectItemsDropdown("//ng-select[@bindvalue='provinceCode']//span[@class='ng-arrow-wrapper']",
                "//div[@role='option']/span","Tỉnh Hà Giang");
        sleepSecond(3);

        Assert.assertEquals(driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[contains(@class,'ng-value-label')]")).getText(),"Tỉnh Hà Giang");


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

    public void selectItemsDropdown(String xpathParents,String xpathChild, String items){
        driver.findElement(By.xpath(xpathParents)).click();
        sleepSecond(2);

        //chờ cho tất cả các items hiển thị
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathChild)));

        //lấy tất cả các giá trị trong dropdown
        childItems = driver.findElements(By.xpath(xpathChild));

        //duyệt qua các items
        for (WebElement element:childItems){
            //kiểm tra có cái element mình chọn
            if (element.getText().trim().equals(items)){
                element.click();
                sleepSecond(4);
                //thoát vòng lặp khi thỏa điều kiện if
                break;
            }
        }
    }
}
