package com.igoldin.qa.school.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {
    @XStreamOmitField
    @Id
    @Column(name = "group_id")
    private int id = Integer.MAX_VALUE;

    @Transient
    private int groupId;

    @Column(name = "group_name")
    private String name;
    @Column(name = "group_header")
    @Type(type = "text")
    private String header;
    @Column(name = "group_footer")
    @Type(type = "text")
    private String footer;

    @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
    private Set<ContactData> contacts = new HashSet<ContactData>();

    public GroupData withId(int id) {
        this.id = id;
        return this;
    }

    public GroupData withGroupId(int id) {
        this.groupId = id;
        return this;
    }

    public GroupData withName(String name) {
        this.name = name;
        return this;
    }

    public GroupData withHeader(String header) {
        this.header = header;
        return this;
    }

    public GroupData withFooter(String footer) {
        this.footer = footer;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {       // replaced ContactData with String
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public Set<ContactData> getContacts() {
        return new Contacts(contacts);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;

        if (id != groupData.id) return false;
        return name != null ? name.equals(groupData.name) : groupData.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public int getGroupId() {
        return groupId;
    }
}
