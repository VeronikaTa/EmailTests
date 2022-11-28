package com.EmailServerLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailPageAOL {
    WebDriver driver;
    WebDriverWait wait;

    private By receivedMessage = By.xpath("//ul[@aria-label='Message list']/li[1]");
    private By userName = By.xpath("//ul[@aria-label='Message list']/li//descendant::strong");
    private By subject = By.xpath("//span[@data-test-id='message-subject']");
    private By snippet = By.xpath("//div[@data-test-id='snippet']");

    public MailPageAOL(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public String getActualUserName(){
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(userName));
        List<WebElement> userNames = driver
                .findElements(userName);
       return userNames.get(0)
                .getAttribute("innerText");
    }

    public String getActualSubject(){
        List<WebElement> subjects = driver
                .findElements(subject);
        return subjects
                .get(0)
                .getAttribute("innerText");
    }

    public String getMessageSnippet(){
        List<WebElement> messageSnippets = driver.findElements(snippet);
        return messageSnippets
                .get(0)
                .getAttribute("innerText");
    }

}
