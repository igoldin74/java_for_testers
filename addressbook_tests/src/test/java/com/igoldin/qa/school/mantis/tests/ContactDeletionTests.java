package com.igoldin.qa.school.mantis.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().groupPage();
            if (app.db().groups().size() == 0) {
                app.group().create(new GroupData().withName("test_group").withHeader("test_group").withFooter("test_group"));
            }
            Groups groups = app.db().groups();
            ContactData inGroup = new ContactData().inGroup(groups.iterator().next());
            ContactData contact = new ContactData();
            app.contact().create(contact.withFirst_name("Rand").withLast_name("McNally")
                    .withHome_phone("7732943449").withEmail1("test@testing.com"), true);
        }
    }
    
    @Test
    public void testContactDeletion() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.goTo().homePage();
        app.contact().removeContact(deletedContact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
        assertThat(after, equalTo(before.withOut(deletedContact)));
        verifyContactListInUI();
    }

}
