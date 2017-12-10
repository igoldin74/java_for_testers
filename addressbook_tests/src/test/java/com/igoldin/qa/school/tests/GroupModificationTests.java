package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));

    }

}
