package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.GroupData;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {


    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("modified_group", "test_H", "test_F"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToGroupPage();
    }


}
