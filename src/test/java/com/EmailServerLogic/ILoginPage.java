package com.EmailServerLogic;

public interface ILoginPage {
     public String getActualInput();
    public String getWarningMessage();
    public String getWarningMessageStyle();
    public ILoginPage enterCredentials(String keys);
    public ILoginPage clickOnNextButton();
}
