package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import com.igoldin.qa.school.model.GroupData;
import com.igoldin.qa.school.model.Groups;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.NoInjection;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        //checking for existing group:
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test_group"));
        }
        Groups groups = app.db().groups();
        ContactData inGroup = new ContactData().inGroup(groups.iterator().next());
    }

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }

    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(@NoInjection ContactData contact) {
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData newContact = new ContactData().withFirst_name("test_firstname").withLast_name("test_lastname").withEmail1("test_email")
                .withAddress("test_address").inGroup(groups.iterator().next());
        app.goTo().homePage();
        app.contact().create(newContact, true);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() + 1);

        assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();


    }

}
