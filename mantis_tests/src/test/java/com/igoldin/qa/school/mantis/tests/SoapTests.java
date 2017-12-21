package com.igoldin.qa.school.mantis.tests;

import com.igoldin.qa.school.mantis.model.Issue;
import com.igoldin.qa.school.mantis.model.ProjectData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws RemoteException, MalformedURLException, ServiceException {
        Set<ProjectData> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (ProjectData project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<ProjectData> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("test_issue").withDescription("test issue description")
                .withProject(projects.iterator().next());
        Issue createdIssue = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), createdIssue.getSummary());
    }
}
