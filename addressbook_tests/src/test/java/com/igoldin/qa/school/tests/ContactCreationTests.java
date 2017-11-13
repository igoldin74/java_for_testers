package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getContactHelper().createNewContact(new ContactData("Rand", null,
                "McNally", null, "7732943449",
                "test@testing.com", "test_group"), true);
        app.getNavigationHelper().goToHomepage();
    }

}
