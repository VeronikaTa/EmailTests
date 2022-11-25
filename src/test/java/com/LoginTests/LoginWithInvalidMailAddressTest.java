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

public class LoginWithInvalidMailAddressTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest()
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://login.yahoo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void verifyImpossibilityOfLoginWithInvalidEmailAddress(){
        EnteringUserNamePageLogic usernamePageLogic = new EnteringUserNamePageLogic(driver, wait);
        String expectedInvalidEmail = createInvalidInput();
        String expectedWarningMessage = "Sorry, we don't recognize this account.";
        String expectedWarningMessageStyle = "rgba(240, 22, 47, 1)";
        String passwordPageURL = "account/challenge/password";

       String actualInput =  usernamePageLogic
               .enterCredentials(expectedInvalidEmail)
               .getActualInput();
       assertEmailEnteredDisplayedCorrectly(actualInput, expectedInvalidEmail);

       usernamePageLogic.clickOnNextButton();

       String actualURL = driver.getCurrentUrl();
       assertNavigationNotPerformed(actualURL, passwordPageURL);

        String actualWarningMessage = usernamePageLogic.getWarningMessage();
       assertWarningMessageText(actualWarningMessage, expectedWarningMessage);

       String actualWarningMessageStyle = usernamePageLogic.getWarningMessageStyle();
       assertWarningMessageStyle(actualWarningMessageStyle, expectedWarningMessageStyle);
    }

    private void assertEmailEnteredDisplayedCorrectly(String actualEmail, String expectedMail){
        if(expectedMail == null){
            throw new IllegalArgumentException("Expected email is null");
        }
        if(expectedMail.isBlank()){
            throw new IllegalArgumentException("Expected email is blank");
        }
        Assert.assertEquals(actualEmail,expectedMail);
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
    public  static String createInvalidInput(){
        int numberOfRepetitions = (int) (Math.random()*10+1);
        StringBuffer invalidInput = new StringBuffer();
        char[] letters = {'a', 'b','c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        for(int i = 0; i < numberOfRepetitions; i++){
            int randomNumber;
            int randomLetterIndex;
            randomNumber = (int)(Math.random()*10+1);
            invalidInput.append(randomNumber);
            randomLetterIndex = (int) (Math.random()*26);
            invalidInput.append(letters[randomLetterIndex]);
        }

        return invalidInput.toString();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
