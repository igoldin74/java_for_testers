package com.igoldin.qa.school.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().confirmActionOnPopup();
        app.getNavigationHelper().goToHomepage();
    }

}
