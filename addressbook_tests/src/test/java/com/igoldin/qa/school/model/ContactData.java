package com.igoldin.qa.school.model;

public class ContactData {
    private final String id;
    private final String first_name;
    private final String middle_name;
    private final String last_name;
    private final String company;
    private final String home_phone;
    private final String email1;
    private String group;

    public ContactData(String id, String first_name, String middle_name, String last_name,
                       String company, String home_phone, String email1, String group) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.company = company;
        this.home_phone = home_phone;
        this.email1 = email1;
        this.group = group;
        this.id = id;
    }

    public String getId() {
        return id;
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

    public String getEmail1() {
        return email1;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null) return false;
        if (middle_name != null ? !middle_name.equals(that.middle_name) : that.middle_name != null) return false;
        return last_name != null ? last_name.equals(that.last_name) : that.last_name == null;
    }

    @Override
    public int hashCode() {
        int result = first_name != null ? first_name.hashCode() : 0;
        result = 31 * result + (middle_name != null ? middle_name.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        return result;
    }

}