package com.igoldin.qa.school;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    
    @Test
    public void testContactDeletion() {
        selectContact();
        deleteSelectedContacts();
        confirmActionOnPopup();
        goToHomepage();
    }

}
