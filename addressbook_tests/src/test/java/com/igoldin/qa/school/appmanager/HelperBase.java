package com.igoldin.qa.school.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {
    protected FirefoxDriver wd;

    public HelperBase(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    protected void type(String locator, String text) {
        click(By.name(locator));
        wd.findElement(By.name(locator)).clear();
        wd.findElement(By.name(locator)).sendKeys(text);
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }
}
