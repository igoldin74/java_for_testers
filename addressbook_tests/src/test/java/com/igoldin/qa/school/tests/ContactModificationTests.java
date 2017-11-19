package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactModificationTests extends TestBase {


    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            if (! app.getGroupHelper().isThereAGroup()) {
                app.getGroupHelper().createNewGroup(new GroupData("test_group", "test_group", "test_group"));
            }
            app.getContactHelper().createNewContact(new ContactData("test_name", "test_middle_name",
                    "test_last_name", null, "7732943449",
                    "test@testing.com", "test_group"), true);
        }
        app.getNavigationHelper().goToHomepage();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("test_name_updated", "Updated",
                "test_surname", "test_company",
                "7732943449", "test@testing.com", null), false);
        app.getContactHelper().submitUpdatedContact();
        app.getNavigationHelper().goToHomepage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

    }
}
