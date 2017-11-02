package com.igoldin.qa.school;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        initContactCreation();
        fillContactForm(new ContactData("test_name", "\\9", "test_surname", "test_company", "7732943449", "test@testing.com"));
        submitContactForm();
        goToHomepage();
    }

}
