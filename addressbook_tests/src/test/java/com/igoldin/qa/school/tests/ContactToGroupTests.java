package com.igoldin.qa.school.tests;

import com.google.common.collect.Sets;
import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.*;

public class ContactToGroupTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test_group"));
        }
        if (app.db().contacts().size() == 0) {
            ContactData newContact = new ContactData().withFirst_name("test_firstname")
                    .withLast_name("test_lastname").withEmail1("test_email").withAddress("test_address");
            app.contact().create(newContact, true);
        }

    }

    @Test()
    public void testAdditionToGroup() {
        Contacts contacts = app.db().contacts();
        ContactData movedContact = contacts.iterator().next();
        Groups movedContactGroupsBefore = new Groups(movedContact.getGroups());
        Groups allGroups = new Groups(app.db().groups());
        Sets.SetView<GroupData> groupsWithNoContact = Sets.difference(allGroups, movedContactGroupsBefore);
        if (movedContactGroupsBefore.equals(allGroups)) {
            app.goTo().groupPage();
            GroupData newGroup = app.group().create(new GroupData().withName("test_group1"));
            app.goTo().homePage();
            app.contact().moveContactToGroup(movedContact.getId(), newGroup.getGroupId());
        } else {
            if (!movedContactGroupsBefore.equals(allGroups)) {
                for (GroupData g : groupsWithNoContact) {
                    app.goTo().homePage();
                    app.contact().moveContactToGroup(movedContact.getId(), g.getId());
                }

            }

        }

        Groups groupsWithAddedGroups = groupsWithNoContact.copyInto(movedContactGroupsBefore);
        assertTrue(Objects.equals(movedContactGroupsBefore, groupsWithAddedGroups));


    }

    @Test
    public void testRemovalFromGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        GroupData groupToBeRemovedFrom = groups.iterator().next();
        Contacts toBeRemovedFromGroup = new Contacts(groupToBeRemovedFrom.getContacts());
        if (toBeRemovedFromGroup.size() == 0) {
            for (ContactData c : contacts) {
                if (c.getId() < Integer.MAX_VALUE) {
                    app.contact().moveContactToGroup(c.getId(),groupToBeRemovedFrom.getId());
                    app.goTo().homePage();
                }
            }
        }

        for (ContactData co : toBeRemovedFromGroup) {
            if (co.getId() < Integer.MAX_VALUE)
                app.goTo().homePage();
            app.contact().removeContactFromGroup(co.getId(), groupToBeRemovedFrom.getId());
        }

        assertTrue(toBeRemovedFromGroup.size() == 0);

    }


}



