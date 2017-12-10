package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import com.igoldin.qa.school.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().groupPage();
            if (app.db().groups().size() == 0) {
                app.group().create(new GroupData().withName("test_group").withHeader("test_group").withFooter("test_group"));
            }
            app.contact().create(new ContactData().withFirst_name("Rand").withLast_name("McNally")
                    .withHome_phone("7732943449").withEmail1("test@testing.com")
                    .withGroup("test_group"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirst_name(
                modifiedContact.getFirst_name()).withLast_name(
                modifiedContact.getLast_name()).withEmail1(modifiedContact.getEmail1());
        app.contact().modifyContact(modifiedContact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }

}
