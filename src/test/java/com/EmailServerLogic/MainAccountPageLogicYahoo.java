package com.EmailServerLogic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainAccountPageLogicYahoo {
    private WebDriver driver;
    private WebDriverWait wait;

    private By mailButton = By.xpath("//div[contains(@class,'navigation')]/ul/li[1]/a");

    public MainAccountPageLogicYahoo(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public MainAccountPageLogicYahoo clickMailButton(){
        driver
                .findElement(mailButton)
                .click();
        return new MainAccountPageLogicYahoo(driver, wait);
    }
}
