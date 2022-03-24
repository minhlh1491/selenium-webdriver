package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    String petrolCheckbox147 = "//input[@id='engine3']";
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

    public void TC_02_Defaut_Checkbox_Radio(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        By scrollCheckbox = By.xpath("//h1[@class='kd-title']");

        jsExecutor.executeScript("arguments[0].scrollIntoView();",driver.findElement(scrollCheckbox));

        By dualZone_checkbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        driver.findElement(dualZone_checkbox).click();

        Assert.assertTrue(driver.findElement(dualZone_checkbox).isSelected());
        sleepSecond(2);

        driver.findElement(dualZone_checkbox).click();
        Assert.assertFalse(driver.findElement(dualZone_checkbox).isSelected());
        sleepSecond(2);

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath(petrolCheckbox147)));

        if (!driver.findElement(By.xpath(petrolCheckbox147)).isSelected()){
            jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath(petrolCheckbox147)));
        }


    }

    public void TC_03_CustomCheclbox_radio(){
        driver.get("https://material.angular.io/components/radio/examples");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        By summerRadio = By.xpath("//span[contains(text(),'Summer')]/preceding-sibling::span//input");

        jsExecutor.executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//label[@id='example-radio-group-label']")));

        sleepSecond(2);
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(summerRadio));

        if (!driver.findElement(summerRadio).isSelected()){
            System.out.println("checkbox is not selected, try again");
            jsExecutor.executeScript("arguments[0].click();",driver.findElement(summerRadio));
        }

        driver.get("https://material.angular.io/components/checkbox/examples");

        By checkedCheckbox = By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input");
        By indeterminateCheckbox = By.xpath("//span[contains(text(),'Indeterminate')]/preceding-sibling::span/input[@id='mat-checkbox-2-input']");

        jsExecutor.executeScript("arguments[0].click();",driver.findElement(checkedCheckbox));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(indeterminateCheckbox));

        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        jsExecutor.executeScript("arguments[0].click();",driver.findElement(checkedCheckbox));
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(indeterminateCheckbox));

        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());


    }

    public void TC_04_CustomCheckbox_radio(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canThoCheckbox = By.xpath("//div[@data-value='Cần Thơ']");

        Assert.assertFalse(driver.findElement(canThoCheckbox).isSelected());

        driver.findElement(canThoCheckbox).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());



    }

    public void TC_05_Accept_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        By jsAlert = By.xpath("//button[contains(text(),'Click for JS Alert')]");
        driver.findElement(jsAlert).click();
        driver.switchTo().alert().accept();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
    }

    @Test
    public void TC_06_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        By jsConfirm = By.xpath("//button[contains(text(),'Click for JS Confirm')]");

        driver.findElement(jsConfirm).click();
        Assert.assertEquals(driver.switchTo().alert().getText(),"I am a JS Confirm");

        driver.switchTo().alert().dismiss();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");
    }

    public void sleepSecond(long second){
        try{
            Thread.sleep(second * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
