package com.igoldin.qa.school.tests;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactFormEntriesTests extends TestBase {

    @BeforeMethod
    public void applyPreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().groupPage();
            if (app.group().all().size() == 0) {
                app.group().create(new GroupData().withName("test_group")
                        .withHeader("test_group").withFooter("test_group"));
            }
            app.contact().create(new ContactData().withFirst_name("Rand").withLast_name("McNally")
                    .withHome_phone("7732943449").withEmail1("test@testing.com")
                    .withGroup("test_group"), true);
        }
        app.goTo().homePage();
    }

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAll_phones(), equalTo(mergedPhones(contactInfoFromEditForm)));

    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]","");
    }

    private String mergedPhones (ContactData contact) {
        return Arrays.asList(contact.getHome_phone(), contact.getMobile_phone(), contact.getWork_phone())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactFormEntriesTests::cleaned)
                .collect(Collectors.joining("\n"));

    }


    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAll_emails(), equalTo(mergedEmails(contactInfoFromEditForm)));

    }

    private String mergedEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(formatted(contactInfoFromEditForm)));
    }


    private String formatted(ContactData contactInfoFromEditForm) {
            return Stream.of(contactInfoFromEditForm.getAddress()).filter((s) -> ! s.equals(""))
                    .collect(Collectors.joining("\n"));
    }

}


