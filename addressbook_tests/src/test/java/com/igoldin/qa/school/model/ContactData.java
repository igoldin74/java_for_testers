package com.igoldin.qa.school.model;

public class ContactData {
    private final String first_name;
    private final String middle_name;
    private final String last_name;
    private final String company;
    private final String home_phone;
    private final String email1;
    private String group;

    public ContactData(String first_name, String middle_name, String last_name,
                       String company, String home_phone, String email1, String group) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.company = company;
        this.home_phone = home_phone;
        this.email1 = email1;
        this.group = group;
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
}
