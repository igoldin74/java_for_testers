package com.igoldin.qa.school.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() {
        app.selectContact();
        app.deleteSelectedContacts();
        app.confirmActionOnPopup();
        app.goToHomepage();
    }

}
