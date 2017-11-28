package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (! app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToGroupPage();
            if (! app.getGroupHelper().isThereAGroup()) {
                app.getGroupHelper().createNewGroup(new GroupData(0,"test_group", "test_group", "test_group"));
            }
            app.getContactHelper().createNewContact(new ContactData(0,"Rand", null,
                    "McNally", null, "7732943449",
                    "test@testing.com", "test_group"), true);
        }
        app.getNavigationHelper().goToHomepage();
    }
    
    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size();
        app.getContactHelper().removeContact(index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), index - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }

}
