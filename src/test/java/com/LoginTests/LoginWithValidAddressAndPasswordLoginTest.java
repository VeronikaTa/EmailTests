package com.LoginTests;

import com.EmailServerLogic.EnteringUserNamePageLogic;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginWithValidAddressAndPasswordLoginTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest()
    public void setUpTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://login.yahoo.com/");
    }

    @Test()
    public void verifyLoginWithValidAddressAndPasswordTest(){
        String mailAddress = "testingepam@yahoo.com";
        String password = "testing123EPAM";

        new EnteringUserNamePageLogic(driver,wait)
                .enterCredentials(mailAddress)
                .clickOnNextButton()
                .enterCredentials(password)
                .verifyIfToggled()
                .clickOnNextButton();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://it.yahoo.com/?p=us";
        Assert.assertEquals(actualURL,expectedURL);
    }

    @AfterTest()
    public void afterTest(){
        driver.quit();
    }
}
