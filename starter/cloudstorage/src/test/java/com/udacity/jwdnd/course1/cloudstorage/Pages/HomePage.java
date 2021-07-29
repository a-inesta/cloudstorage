package com.udacity.jwdnd.course1.cloudstorage.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(id = "noteEdit1")
    private WebElement editNote1;

    @FindBy(id = "noteDelete")
    private WebElement deleteNote1;



    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void addNewNote(String title, String description) {
        toNoteTab.click();
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
        toNoteTab.click();
        editNote1.click();
        noteTitle.sendKeys(title);
        noteDescription.sendKeys(description);
        submitNote.submit();
    }

}
