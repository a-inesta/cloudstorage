package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUsername;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submit;

    @FindBy(id = "error-msg")
    private WebElement error;

    @FindBy(id = "success-msg")
    private WebElement success;

    @FindBy(id = "login-link")
    private WebElement login;


    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputPassword.sendKeys(password);
        inputUsername.sendKeys(username);
        submit.submit();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        login.click();
    }


}
