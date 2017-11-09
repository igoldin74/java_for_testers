package com.igoldin.qa.school.appmanager;

import com.igoldin.qa.school.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper (WebDriver wd) {
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
        click(By.xpath("//div[@id='nav']//a[.='add new']"));
    }

    public void selectContact() {
        click(By.id("4"));
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
}
