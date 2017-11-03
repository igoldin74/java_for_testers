package com.igoldin.qa.school.appmanager;

import com.igoldin.qa.school.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper (FirefoxDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type("firstname", contactData.getFirst_name());
        type("middlename", contactData.getMiddle_name());
        type("lastname", contactData.getLast_name());
        type("company", contactData.getCompany());
        type("home", contactData.getHome_phone());
        type("email", contactData.getEmail1());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.id("3"));

    }

    public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }
}
