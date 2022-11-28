package com.ReceivengEmailTests;

import com.EmailServerLogic.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class SendingAnEmailTest {
    private WebDriver driver;
    private WebDriverWait wait;

  private final  String emailAddressYahoo = "testingepam@yahoo.com";
  private final  String passwordYahoo = "testing123EPAM";
  private  String expectedSubject = "Testing";
  private String expectedText = "Message sent";
  private String emailAddressAol = "testerv@aol.com";
  private String passwordAol = "6n7$gPkXP*8!H5z";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Test(groups = "Sending")
    public void verifySendingEmail(){
        driver.get("https://login.yahoo.com/");
        driver.manage().window().maximize();

        login(emailAddressYahoo, passwordYahoo);
        new MainAccountPageLogicYahoo(driver, wait)
                .clickMailButton();

        MailPageLogicYahoo mailPageLogicYahoo = new MailPageLogicYahoo(driver, wait);

        mailPageLogicYahoo
                .clickComposeButton()
                .clickToInputField()
                .typeReceiverAddress(emailAddressAol)
                .clickSubjectInputField()
                .typeSubject(expectedSubject)
                .typeText(expectedText);

        String displayedReceiverAddress = mailPageLogicYahoo.getDisplayedToAddress();
        String displayedSubject = mailPageLogicYahoo.getActualSubject();
        String displayedText = mailPageLogicYahoo.getActualText();

        Assert.assertEquals(displayedReceiverAddress,emailAddressAol);
        Assert.assertEquals(displayedSubject,expectedSubject);
        Assert.assertEquals(displayedText,expectedText);

        mailPageLogicYahoo.clickSendButton();
    }

    private void login (String mailAddress, String password){
                new EnteringUserNamePageLogic(driver, wait)
                        .enterCredentials(mailAddress)
                        .clickOnNextButton()
                        .enterCredentials(password)
                        .verifyIfToggled()
                        .clickOnNextButton();
            }
        }