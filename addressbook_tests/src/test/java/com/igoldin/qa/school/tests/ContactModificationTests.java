package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test_group")
                        .withHeader("test_group").withFooter("test_group"));
            }
            app.contact().create(new ContactData().withFirst_name("Rand").withLast_name("McNally")
                    .withHome_phone("7732943449").withEmail1("test@testing.com")
                    .withGroup("test_group"), true);
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        int index = before.size();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirst_name(
                modifiedContact.getFirst_name()).withLast_name(
                modifiedContact.getLast_name()).withEmail1(modifiedContact.getEmail1());
        app.contact().modifyContact(index, contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());


        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }

}
