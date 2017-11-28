package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

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
    public void testContactModification() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size();
        ContactData contact = new ContactData(before.get(index - 1).getId(),
                before.get(index - 1).getFirst_name(), null,
                before.get(index - 1).getLast_name(), "test_company",
                "7732943449_edited", before.get(index - 1).getEmail1(), null);
        app.getContactHelper().modifyContact(index, contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }

}
