package com.yahooEmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnteringUserNamePageLogic {

    private WebDriver driver;
    private WebDriverWait wait;

    private  By inputField = By.id("login-username");
    private final By nextButton = By.id("login-signin");
    private final By warningMessage = By.id("username-error");
    public EnteringUserNamePageLogic(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public EnteringUserNamePageLogic enterCredentials(String keys){
       WebElement inputFieldElement = driver.findElement(inputField);
       inputFieldElement.click();
       inputFieldElement.sendKeys(keys);
        return this;
    }
    public EnteringPasswordPageLogic clickOnNextButton(){
        driver.findElement(nextButton).click();
        return new EnteringPasswordPageLogic(driver,wait);
    }

    public String getActualInput(){
        return driver.findElement(inputField).getDomProperty("value");
    }

    public String getWarningMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage)).getAttribute("innerText");
    }

    public String getWarningMessageStyle(){
        return driver.findElement(warningMessage).getCssValue("color");
    }
}
