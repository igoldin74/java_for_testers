package com.igoldin.qa.school.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contacts")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    private String first_name;

    @Transient
    private String middle_name;

    @Column(name = "lastname")
    private String last_name;

    private String company;

    @Column(name = "home")
    @Type(type = "text")
    private String home_phone;

    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile_phone;

    @Column(name = "work")
    @Type(type = "text")
    private String work_phone;

    @Transient
    private String all_phones;

    @Column(name = "email")
    @Type(type = "text")
    private String email1;

    @Transient
    private String email2;

    @Transient
    private String email3;

    @Transient
    private String all_emails;

    @Column
    @Type(type = "text")
    private String address;

    @XStreamOmitField
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();



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

    public ContactData inGroup(GroupData group) {
        groups.add(group);
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

    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(first_name, that.first_name) &&
                Objects.equals(last_name, that.last_name) &&
                Objects.equals(email1, that.email1) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, first_name, last_name, email1, address);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email1='" + email1 + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

}