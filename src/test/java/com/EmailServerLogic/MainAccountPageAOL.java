package com.EmailServerLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainAccountPageAOL {
 private   WebDriver driver;
    private WebDriverWait wait;

  private  By mailButton = By.xpath("//a[@title='email link']");

    public MainAccountPageAOL(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public MainAccountPageAOL clickMailButton(){
        driver
                .findElement(mailButton)
                .click();
        return new MainAccountPageAOL(driver, wait);
    }
}
