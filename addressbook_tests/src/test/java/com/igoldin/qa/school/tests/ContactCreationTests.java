package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("test_name", "\\9", "test_surname", "test_company", "7732943449", "test@testing.com"));
        app.getContactHelper().submitContactForm();
        app.getNavigationHelper().goToHomepage();
    }

}
