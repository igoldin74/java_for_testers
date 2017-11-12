package com.igoldin.qa.school.appmanager;

import com.igoldin.qa.school.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.naming.Name;
import java.util.NoSuchElementException;

public class ContactHelper extends HelperBase {

    private ContactData contact;

    public ContactHelper (WebDriver wd) {
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
        }   else {
            Assert.assertFalse(isElementPresent(By.name("new_grup")));
            }
        }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
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

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createNewContact(ContactData contact, boolean b) {
        initContactCreation();
        fillContactForm(contact, b);
        submitContactForm();
    }
}
