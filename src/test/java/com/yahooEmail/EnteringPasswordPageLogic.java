package com.yahooEmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EnteringPasswordPageLogic {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By passwordField = By.id("login-passwd");
    private final By nextButton = By.id("login-signin");
    private final By passwordContainer = By.id("password-container");

    private final By warningMessage = By.xpath("//p[@class='error-msg']");

    public EnteringPasswordPageLogic(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public EnteringPasswordPageLogic enterPassword(String password){
        WebElement passwordFieldElement = driver.findElement(passwordField);
        passwordFieldElement.click();
        passwordFieldElement.sendKeys(password);
        return this;
    }
    public EnteringPasswordPageLogic verifyIfToggled(){
       String actualInputType = driver.findElement(passwordField)
               .getDomProperty("type");
       Assert.assertEquals(actualInputType,"password");
    return this;
    }

    public MainAccountPageLogic clickOnNextButton(){
        driver.findElement(nextButton).click();
        return new MainAccountPageLogic(driver, wait);
    }

    public String getPasswordBorderStyle(){
        return driver.findElement(passwordContainer).getCssValue("border-bottom");
    }

    public String getWarningMessage(){
      return  driver.findElement(warningMessage).getAttribute("innerText");
    }

    public String getWarningMessageColor(){
        return driver.findElement(warningMessage).getCssValue("color");
    }

    public String getActualInput(){
      return  driver.findElement(passwordField).getDomProperty("value");
    }
    public EnteringPasswordPageLogic clickOnINputField(){
        driver.findElement(passwordField).click();
    return this;
    }
}
