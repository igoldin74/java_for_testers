package com.igoldin.qa.school.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper (WebDriver wd) {
        super(wd);
    }

    public void goToHomepage() {
        click(By.linkText("home"));
    }

    public void returnToGroupPage() {
        click(By.linkText("groups"));
    }

    public void goToGroupPage() {
        click(By.linkText("groups"));
    }

    public void confirmActionOnPopup() {
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
