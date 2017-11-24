package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {


    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            if (! app.getGroupHelper().isThereAGroup()) {
                app.getGroupHelper().createNewGroup(new GroupData(null,"test_group", "test_group", "test_group"));
            }
            app.getContactHelper().createNewContact(new ContactData(null,"Rand", null,
                    "McNally", null, "7732943449",
                    "test@testing.com", "test_group"), true);
        }
        app.getNavigationHelper().goToHomepage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);

        ContactData contact = new ContactData(before.get(before.size() - 1).getId(),
                before.get(before.size() - 1).getFirst_name(), null,
                before.get(before.size() - 1).getLast_name(), "test_company",
                "7732943449_edited", before.get(before.size() - 1).getEmail1(), null);

        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitUpdatedContact();
        app.getNavigationHelper().goToHomepage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() - 1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
