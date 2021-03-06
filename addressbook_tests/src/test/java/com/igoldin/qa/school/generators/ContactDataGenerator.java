package com.igoldin.qa.school.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.igoldin.qa.school.model.ContactData;
import com.igoldin.qa.school.model.Groups;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static com.igoldin.qa.school.tests.TestBase.app;

public class ContactDataGenerator {

    //default arguments: -c 1 -f addressbook_tests/src/test/resources/contacts.xml -e xml

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-e", description = "File format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
        }
        generator.run();

    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContactData(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        }

    }

    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s\n", contact.getFirst_name(), contact.getLast_name(),
                        contact.getEmail1(), contact.getAddress()));
            }

        }


    }

    private void saveAsXml(List <ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        XStream.setupDefaultSecurity(xstream);
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }


    }

    private static List<ContactData> generateContactData(int count) {
        Groups groups = app.db().groups();
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirst_name(String.format("test_name %s", i))
                    .withLast_name(String.format("test_lastname %s", i)).withEmail1(String.format("test_email1 %s", i))
                    .withAddress(String.format("test_address %s", i)).inGroup(groups.iterator().next()));
        }
        return contacts;

    }
}
