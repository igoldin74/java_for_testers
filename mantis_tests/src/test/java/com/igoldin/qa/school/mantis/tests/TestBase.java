package com.igoldin.qa.school.mantis.tests;

import com.igoldin.qa.school.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {

    public static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX)); //2nd param default property


    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}


