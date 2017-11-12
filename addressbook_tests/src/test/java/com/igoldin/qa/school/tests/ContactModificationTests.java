package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {


    @Test
    public void testContactModification() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createNewContact(new ContactData("test_name", null,
                    null, null, "7732943449",
                    "test@testing.com", "test_group"), true);
        }
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("test_name_updated", "Updated",
                "test_surname", "test_company",
                "7732943449", "test@testing.com", null), false);
        app.getContactHelper().submitUpdatedContact();
    }
}
