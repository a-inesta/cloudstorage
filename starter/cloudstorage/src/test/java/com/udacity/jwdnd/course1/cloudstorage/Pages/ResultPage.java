package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "success-msg")
    private WebElement successMsg;

    @FindBy(id = "error-msg")
    private WebElement errorMsg;

    @FindBy(id = "success-continue")
    private WebElement successCnt;

    @FindBy(id = "error-continue")
    private WebElement errorCnt;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void toHomePage() {
        if (successCnt != null) {
            successCnt.click();
        } else if (errorCnt != null){
            errorCnt.click();
        } else {
            System.out.println("Neither success nor error exists.");
        }
    }
}
