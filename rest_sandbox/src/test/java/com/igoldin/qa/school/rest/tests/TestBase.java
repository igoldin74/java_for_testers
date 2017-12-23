package com.igoldin.qa.school.rest.tests;

import com.igoldin.qa.school.rest.appmanager.ApplicationManager;
import org.testng.SkipException;

import java.io.IOException;


public class TestBase {

    public static ApplicationManager app = new ApplicationManager(); //2nd param default property

    private boolean isIssueOpen(int issueId) throws IOException {
        int state = app.rest().getIssueState(issueId);
        if (state < 2) {
            return true;
        }
        return false;

    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}