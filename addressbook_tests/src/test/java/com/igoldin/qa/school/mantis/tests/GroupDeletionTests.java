package com.igoldin.qa.school.mantis.tests;

import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test_group"));
        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().groupPage();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyGrouplistInUI();


    }

}
