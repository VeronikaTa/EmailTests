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

public class LoginWithSpacesForEmailAddressTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://login.yahoo.com/");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    @Test
    public void verifyImpossibilityOfLoginWithBlankSpacesAsAddress(){
        String passwordPageURL = "account/challenge/password";
        String expectedWarningMessage = "Sorry, we don't recognize this email.";
        String expectedWarningMessageStyle = "rgba(240, 22, 47, 1)";

        EnteringUserNamePageLogic userNamePageLogic = new EnteringUserNamePageLogic(driver, wait);
        userNamePageLogic.enterCredentials(" ")
                .clickOnNextButton();
        String actualURL = driver.getCurrentUrl();
        assertNavigationNotPerformed(actualURL, passwordPageURL);
        String  actualWarningMessage = userNamePageLogic.getWarningMessage();
        assertWarningMessageText(actualWarningMessage,expectedWarningMessage);
        String actualWarningMessageStyle = userNamePageLogic.getWarningMessageStyle();
        assertWarningMessageStyle(actualWarningMessageStyle, expectedWarningMessageStyle);
    }
    private void assertWarningMessageText(String actualWarningMessageText, String expectedWarningMessageText){
        Assert.assertEquals(actualWarningMessageText.replaceAll("\u00A0", " "), expectedWarningMessageText);
    }

    private void assertWarningMessageStyle(String actualWarningMessageStyle, String expectedWarningMessageStyle){
        Assert.assertEquals(actualWarningMessageStyle, expectedWarningMessageStyle);
    }

    private void assertNavigationNotPerformed(String actualURL, String expectedURL){
        Assert.assertFalse(actualURL.contains(expectedURL));
    }
    @AfterTest()
    public void teardown(){
        driver.quit();
    }
}
