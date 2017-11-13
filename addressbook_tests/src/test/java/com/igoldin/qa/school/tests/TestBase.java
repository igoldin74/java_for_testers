package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sun.plugin2.util.BrowserType;

import static org.openqa.selenium.remote.BrowserType.*;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(FIREFOX);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}
