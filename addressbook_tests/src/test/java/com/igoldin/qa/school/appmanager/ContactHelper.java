package com.igoldin.qa.school.appmanager;

import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        type("home", contactData.getHome_phone());
        type("email", contactData.getEmail1());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));

        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void modifyContact(int index, ContactData contact) {
        initContactModification(index - 1);
        fillContactForm(contact, false);
        submitUpdatedContact();
        app.goTo().homePage();
    }

    public void removeContact(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        app.goTo().confirmActionOnPopup();
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

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact, b);
        submitContactForm();
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();

    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String first_name = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[3]")).getText();
            String last_name = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[2]")).getText();
            String email = element.findElement(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[5]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirst_name(first_name)
                    .withLast_name(last_name).withEmail1(email);
            contacts.add(contact);
        }
        return contacts;
    }

}



