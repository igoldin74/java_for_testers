package com.igoldin.qa.school.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }
}
