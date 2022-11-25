package com.EmailServerLogic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainAccountPageLogic {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainAccountPageLogic(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }
}
