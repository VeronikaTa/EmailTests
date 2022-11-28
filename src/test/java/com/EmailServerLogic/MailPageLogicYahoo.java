package com.EmailServerLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPageLogicYahoo {
    private WebDriver driver;
    private WebDriverWait wait;

    By composeButton = By.xpath("//a[@data-test-id ='compose-button']");
    By toInputField = By.id("message-to-field");
    By pillText = By.xpath("//div[@data-test-id='pill-text']");
    By subjectInputField = By.xpath("//input[@data-test-id='compose-subject']");
    By textBox = By.xpath("//div[@data-test-id='rte']");
    By sendButton = By.xpath("//button[@data-test-id='compose-send-button']");

    public MailPageLogicYahoo(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
    public MailPageLogicYahoo clickComposeButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(composeButton))
                .click();
        return this;
    }
    public MailPageLogicYahoo clickToInputField(){
        driver
                .findElement(toInputField)
                .click();
        return this;
    }
    public MailPageLogicYahoo typeReceiverAddress(String keys){
        driver
                .findElement(toInputField)
                .sendKeys(keys);
        return this;
    }
    public String getDisplayedToAddress(){
      return  driver
              .findElement(pillText)
                .getAttribute("innerText");
    }

    public MailPageLogicYahoo clickSubjectInputField(){
        driver
                .findElement(subjectInputField)
                .click();
        return this;
    }

    public MailPageLogicYahoo typeSubject(String keys){
        driver
                .findElement(subjectInputField)
                .sendKeys(keys);
        return this;
    }

    public String getActualSubject(){
        return driver
                .findElement(subjectInputField)
                .getDomProperty("defaultValue");
    }

    public MailPageLogicYahoo clickTextBox(){
        driver
                .findElement(textBox)
                .click();
        return this;
    }

    public MailPageLogicYahoo typeText(String keys){
        driver
                .findElement(textBox)
                .sendKeys(keys);
        return this;
    }

    public String getActualText(){
        return driver
                .findElement(textBox)
                .getDomProperty("innerText");
    }

    public void clickSendButton(){
        driver
                .findElement(sendButton)
                .click();
    }
}
