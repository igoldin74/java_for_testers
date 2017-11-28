package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import sun.plugin2.util.BrowserType;

import static org.openqa.selenium.remote.BrowserType.*;

public class TestBase {

    public static ApplicationManager app = new ApplicationManager(FIREFOX);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

}
