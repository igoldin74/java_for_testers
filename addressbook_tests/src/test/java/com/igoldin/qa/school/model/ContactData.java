package com.igoldin.qa.school.model;

import java.util.stream.Collectors;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String first_name;
    private String middle_name;
    private String last_name;
    private String company;
    private String home_phone;
    private String mobile_phone;
    private String work_phone;
    private String all_phones;
    private String email1;
    private String email2;
    private String email3;
    private String all_emails;
    private String address;
    private String group;



    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public ContactData withMiddle_name(String middle_name) {
        this.middle_name = middle_name;
        return this;
    }

    public ContactData withLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public ContactData withHome_phone(String home_phone) {
        this.home_phone = home_phone;
        return this;
    }

    public ContactData withMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
        return this;
    }

    public ContactData withWork_phone(String work_phone) {
        this.work_phone = work_phone;
        return this;
    }

    public ContactData withAll_phones(String all_phones) {
        this.all_phones = all_phones;
        return this;
    }

    public ContactData withAll_emails(String all_emails) {
        this.all_emails = all_emails;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withEmail1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }



    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getCompany() {
        return company;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public String getWork_phone() {
        return work_phone;
    }

    public String getAll_phones() {
        return all_phones;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAll_emails() {
        return all_emails;
    }

    public String getGroup() {
        return group;
    }



    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", home_phone='" + home_phone + '\'' +
                ", email1='" + email1 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null) return false;
        if (home_phone != null ? !home_phone.equals(that.home_phone) : that.home_phone != null) return false;
        return email1 != null ? email1.equals(that.email1) : that.email1 == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (home_phone != null ? home_phone.hashCode() : 0);
        result = 31 * result + (email1 != null ? email1.hashCode() : 0);
        return result;
    }
}