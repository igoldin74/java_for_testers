package com.igoldin.qa.school.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {

    private FirefoxDriver wd;

    public NavigationHelper (FirefoxDriver wd) {
        this.wd = wd;
    }

    public void goToHomepage() {
        wd.findElement(By.linkText("home")).click();
    }

    public void returnToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }

    public void goToGroupPage() {
        wd.findElement(By.linkText("groups")).click();
    }
}
