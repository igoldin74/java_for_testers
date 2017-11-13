package com.igoldin.qa.school.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void type(String locator, String text) {
        click(By.name(locator));
        if (text != null) {
            String existingText = wd.findElement(By.name(locator)).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(By.name(locator)).clear();
                wd.findElement(By.name(locator)).sendKeys(text);

            }
        }
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        }
            catch (NoSuchElementException ex) {
            return false;
        }
    }
}
