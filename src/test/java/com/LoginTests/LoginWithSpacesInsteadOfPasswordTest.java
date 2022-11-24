package com.LoginTests;

import com.yahooEmail.EnteringPasswordPageLogic;
import com.yahooEmail.EnteringUserNamePageLogic;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginWithSpacesInsteadOfPasswordTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest()
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.yahoo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test()
    public void verifyImpossibilityOfLoginWithSpacesInsteadOfPassword(){
        String address = "testingepam@yahoo.com";
        String expectedBorderStyle = "1px solid rgb(255, 51, 58)";
        String expectedWarningMessage = "Please provide password.";
        String expectedWarningMessageColor = "rgba(240, 22, 47, 1)";

   String actualInput =     new EnteringUserNamePageLogic(driver,wait)
                .enterCredentials(address)
                .clickOnNextButton()
                .enterPassword(" ")
                .verifyIfToggled()
                .getActualInput();
   assertInput(actualInput, " ");

   new EnteringPasswordPageLogic(driver, wait)
                .clickOnNextButton();

        assertNoNavigationDone();

        EnteringPasswordPageLogic enteringPasswordPageLogic = new EnteringPasswordPageLogic(driver, wait);

        assertBorderStyle(enteringPasswordPageLogic, expectedBorderStyle);

        assertWarningMessage(enteringPasswordPageLogic,expectedWarningMessage);

        assertWarningMessageColor(enteringPasswordPageLogic, expectedWarningMessageColor);
    }

    public void assertNoNavigationDone(){
        String mainAccountPageURL = "https://it.yahoo.com/?p=us";
        String actualURL = driver.getCurrentUrl();
        Assert.assertNotEquals(actualURL, mainAccountPageURL);
    }

    public void assertBorderStyle(EnteringPasswordPageLogic enteringPasswordPageLogic, String expectedBorderStyle){
        String actualBorderStyle = enteringPasswordPageLogic.getPasswordBorderStyle();
        Assert.assertEquals(actualBorderStyle,expectedBorderStyle);
    }

    public void assertWarningMessage(EnteringPasswordPageLogic enteringPasswordPageLogic, String expectedWarningMessage){
        String actualWarningMessage = enteringPasswordPageLogic.getWarningMessage().replaceAll("\u00A0"," ");
        Assert.assertEquals(actualWarningMessage, expectedWarningMessage);
    }

    public void assertWarningMessageColor(EnteringPasswordPageLogic enteringPasswordPageLogic, String expectedWarningMessageColor){
        String actualWarningMessageColor = enteringPasswordPageLogic.getWarningMessageColor();
        Assert.assertEquals(actualWarningMessageColor,expectedWarningMessageColor);
    }

    public void assertInput(String actualInput, String expectedInput){
        Assert.assertEquals(actualInput, expectedInput);
    }

    @AfterTest()
    public void afterTest(){
        driver.quit();
    }
}

