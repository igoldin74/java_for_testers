package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GroupModificationTests extends TestBase {


    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createNewGroup(new GroupData("test_group", "none", "none"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("modified_group", "test_group", "test_group"));
        app.getGroupHelper().submitGroupModification();
        app.getNavigationHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());
    }


}
