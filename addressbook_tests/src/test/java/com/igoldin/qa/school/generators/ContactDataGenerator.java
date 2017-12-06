package com.igoldin.qa.school.generators;

import com.igoldin.qa.school.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);


        List<ContactData> contacts = generateContactData(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s; %s; %s; %s; %s\n",contact.getFirst_name(), contact.getLast_name(), contact.getEmail1(),
                    contact.getMobile_phone(), contact.getAddress()));
        }
        writer.close();

    }

    private static List<ContactData> generateContactData(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirst_name(String.format("test_name %s", i))
                    .withLast_name(String.format("test_lastname %s", i)).withEmail1(String.format("test_email1 %s", i))
                    .withHome_phone(String.format("test_homephone %s", i)).withAddress(String.format("test_address %s", i)));
        }
        return contacts;

    }
}
