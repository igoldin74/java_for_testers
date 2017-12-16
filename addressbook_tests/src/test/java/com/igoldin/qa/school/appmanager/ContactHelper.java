package com.igoldin.qa.school.appmanager;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

import static com.igoldin.qa.school.tests.TestBase.app;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type("firstname", contactData.getFirst_name());
        type("middlename", contactData.getMiddle_name());
        type("lastname", contactData.getLast_name());
        type("company", contactData.getCompany());
        type("address", contactData.getAddress());
        type("home", contactData.getHome_phone());
        type("email", contactData.getEmail1());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));

        }
    }

    public void moveContactToGroup(int id, int groupId) {
        //int groupId = Integer.parseInt(wd.findElement(By.tagName("option")).getAttribute("value"));
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
        //wd.findElement(By.name("selected[]")).click();
        wd.findElement(By.name("to_group")).click();
        new Select(wd.findElement(By.name("to_group"))).selectByIndex(groupId);
        wd.findElement(By.name("add")).click();

    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void modifyContact(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitUpdatedContact();
        contactCache = null;
        app.goTo().homePage();
    }

    public void removeContact(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        app.goTo().confirmActionOnPopup();
        contactCache = null;
        app.goTo().homePage();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void submitContactForm() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void submitUpdatedContact() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.cssSelector("a[href^='edit.php?id=']")).get(index).click();
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href^='edit.php?id=%s']", id))).click();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact, b);
        submitContactForm();
        contactCache = null;
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();

    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String first_name = cells.get(2).getText();
            String last_name = cells.get(1).getText();
            String emails = cells.get(4).getText();
            String phones = cells.get(5).getText();
            String address = cells.get(3).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));

            ContactData contact = new ContactData().withId(id).withFirst_name(first_name).withLast_name(last_name)
                    .withEmail1(emails).withAll_emails(emails).withAll_phones(phones).withAddress(address);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();

        return new ContactData().withId(contact.getId()).withFirst_name(firstname).withLast_name(lastname)
                .withHome_phone(homephone).withMobile_phone(mobilephone).withWork_phone(workphone).withEmail1(email1)
                .withEmail2(email2).withEmail3(email3).withAddress(address);
    }

}



