package com.igoldin.qa.school.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void GroupDeletionTests() {
        app.goToGroupPage();
        app.selectGroup();
        app.deleteSelectedGroups();
        app.goToHomepage();
    }

}