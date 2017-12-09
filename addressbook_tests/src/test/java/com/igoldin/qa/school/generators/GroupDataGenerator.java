package com.igoldin.qa.school.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.igoldin.qa.school.model.GroupData;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter (names = "-c", description = "Group count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    @Parameter (names = "-e", description = "File format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
        }
        generator.run();

    }

    private void run() throws IOException {
            List<GroupData> groups = generateGroups(count);
            if (format.equals("csv")) {
                save(groups, new File(file));
            } else if (format.equals("xml")) {
                saveAsXml(groups, new File(file));
            }

    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("testname %s", i))
                    .withHeader(String.format("testheader %s", i)).withFooter(String.format("testfooter %s", i)));
        }
        return groups;

    }

    private void save(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();

    }

    private void saveAsXml(List <GroupData> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();


    }

}
