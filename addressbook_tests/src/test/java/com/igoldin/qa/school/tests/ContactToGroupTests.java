package com.igoldin.qa.school.tests;

import com.google.common.collect.Sets;
import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import com.igoldin.qa.school.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class ContactToGroupTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        //checking for existing group:
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test_group"));
        }


    }


    @Test
    public void testContactToGroup() {
        Contacts contacts = app.db().contacts();
        ContactData movedContact = contacts.iterator().next();
        Set<GroupData> movedContactGroups = new HashSet<GroupData>(movedContact.getGroups());
        Set<GroupData> allGroups = new HashSet<GroupData>(app.db().groups());
        if (movedContactGroups.equals(allGroups)){
            app.goTo().groupPage();
            GroupData newGroup = app.group().create(new GroupData().withName("test_group1"));
            app.goTo().homePage();
            //GroupData newGroup = new GroupData().withName("test_group1");
            app.contact().moveContactToGroup(movedContact.getId(), newGroup.getGroupId());
        } else {
            if (!movedContact.equals(allGroups)) {
                Sets.SetView<GroupData> difference = com.google.common.collect.Sets.difference(allGroups, movedContactGroups);
                GroupData emptyGroups = new GroupData();
                app.contact().moveContactToGroup();
                System.out.println(difference);
            }
        }
        /*Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        ContactData movedContact = contacts.iterator().next();
        Groups groupsId = movedContact.getGroups();
        app.goTo().homePage();
        app.contact().moveContactToGroup(movedContact.getId());
        System.out.println(contacts);
        System.out.println(allGroups);
        System.out.println(movedContactGroups);*/


        }

        /*if (movedContact.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        }

        app.contact().removeContact(deletedContact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(deletedContact)));
        verifyContactListInUI();

    }*/

}


