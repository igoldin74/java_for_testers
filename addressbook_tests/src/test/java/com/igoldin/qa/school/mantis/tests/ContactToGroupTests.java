package com.igoldin.qa.school.mantis.tests;

import com.google.common.collect.Sets;
import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactToGroupTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        //checking for existing group:
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test_group"));
        }

    }

    @Test()
    public void testAdditionToGroup() {
        Contacts contacts = app.db().contacts();
        ContactData movedContact = contacts.iterator().next();
        Groups movedContactGroups = new Groups(movedContact.getGroups());
        Groups allGroups = new Groups(app.db().groups());
        if (movedContactGroups.equals(allGroups)) {
            app.goTo().groupPage();
            GroupData newGroup = app.group().create(new GroupData().withName("test_group1"));
            app.goTo().homePage();
            app.contact().moveContactToGroup(movedContact.getId(), newGroup.getGroupId());

        } else {

            if (!movedContactGroups.equals(allGroups)) {
                Sets.SetView<GroupData> groupsWithNoContact = Sets.difference(allGroups, movedContactGroups);
                groupsWithNoContact.stream().forEach(g ->
                {
                    app.goTo().homePage();
                    app.contact().moveContactToGroup(movedContact.getId(), g.getId());
                });

            }
        }

    }

    @Test(enabled = false)
    public void testRemovalFromGroup() {
        Groups groups = app.db().groups();
        GroupData groupToBeRemovedFrom = groups.iterator().next();
        Contacts toBeRemovedFromGroup = new Contacts(groupToBeRemovedFrom.getContacts());

        toBeRemovedFromGroup.stream().forEach(c ->
        {
            app.goTo().homePage();
            app.contact().removeContactFromGroup(groupToBeRemovedFrom.getId(), c.getId());
        });
    }


}



