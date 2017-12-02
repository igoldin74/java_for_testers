package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test_group");
        app.group().create(group);
        app.goTo().groupPage();
        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        assertThat(after,
                equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
