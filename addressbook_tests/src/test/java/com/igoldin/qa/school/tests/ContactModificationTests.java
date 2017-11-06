package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {


    @Test
    public void testContactModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("test_name_updated", "Updated",
                "test_surname", "test_company", "7732943449", "test@testing.com"));
        app.getContactHelper().submitUpdatedContact();
    }
}
