package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test_group"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test_group")
                .withFooter("test_group").withHeader("test_group");
        app.goTo().groupPage();
        app.group().modify(group);
        Groups after = app.db().groups();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
        verifyGrouplistInUI();

    }

}
