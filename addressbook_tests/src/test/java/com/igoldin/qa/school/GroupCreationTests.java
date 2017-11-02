package com.igoldin.qa.school;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        goToGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test_group", "none", "none"));
        submitGroupCreation();
        returnToGroupPage();
    }

}
