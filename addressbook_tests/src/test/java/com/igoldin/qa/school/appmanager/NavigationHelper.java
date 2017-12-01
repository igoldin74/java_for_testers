package com.igoldin.qa.school.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper (WebDriver wd) {
        super(wd);
    }

    public void homePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home"));
    }

    public void groupPage() {
        click(By.linkText("groups"));
    }

    //public void goToGroupPage() {
    //    if (isElementPresent(By.tagName("h1"))
    //            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
    //            && isElementPresent(By.name("new"))) {
    //     return;
    //    }
    //    click(By.linkText("groups"));
    //}

    public void confirmActionOnPopup() {
        if (isAlertPresent())
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
