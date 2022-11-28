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
import java.util.Iterator;
import java.util.Set;

public class ReceivingAnEmail {  private WebDriver driver;
    private WebDriverWait wait;

    private  String expectedSubject = "Testing";
    private String expectedText = "Message sent";
    private String emailAddressAol = "testerv@aol.com";
    private String passwordAol = "6n7$gPkXP*8!H5z";
    private String senderUserName = "Nika Tarasova";

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    }

    @Test
    public void verifyReceivingEmail() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://login.aol.com/");

        login(emailAddressAol, passwordAol);
        new MainAccountPageAOL(driver, wait)
                .clickMailButton();

        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        Iterator<String> I1 = s.iterator();

        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }

        MailPageAOL mailPageAOL = new MailPageAOL(driver, wait);
        String actualUserName = mailPageAOL.getActualUserName();
        String actualSubject = mailPageAOL.getActualSubject();
        String actualTextSnippet = mailPageAOL.getMessageSnippet();

        Assert.assertEquals(actualUserName, senderUserName);
        Assert.assertEquals(actualSubject, expectedSubject);
        Assert.assertTrue(expectedText.startsWith(actualTextSnippet));
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
