package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.TreeMap;

public class HomePage {
    @FindBy(id = "nav-files-tab")
    private WebElement toFileTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement toNoteTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement toCredentialTab;

    @FindBy(id = "add-new-note")
    private WebElement addNote;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement submitNote;

    @FindBy(id = "editNote1")
    private WebElement editNote1;

    @FindBy(id = "deleteNote1")
    private WebElement deleteNote1;

    @FindBy(id = "success-continue")
    private WebElement successCnt;

    @FindBy(id = "error-continue")
    private WebElement errorCnt;



    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void addNewNote(String title, String description) {
        toNoteTab();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addNote.click();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        submitNote.submit();
    }

    public void editNote(String title, String description) {
        toNoteTab();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editNote1.click();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
        submitNote.submit();
    }

    public void deleteNote(String title, String description) {
        toNoteTab();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        editNote1.click();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        noteTitle.clear();
        noteTitle.sendKeys(title);
        noteDescription.clear();
        noteDescription.sendKeys(description);
        submitNote.submit();
    }

    public void toNoteTab() {
        toNoteTab.click();
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
