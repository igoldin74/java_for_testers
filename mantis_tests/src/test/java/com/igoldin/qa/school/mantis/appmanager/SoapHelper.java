package com.igoldin.qa.school.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import com.igoldin.qa.school.mantis.model.Issue;
import com.igoldin.qa.school.mantis.model.ProjectData;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {

    private ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    public Set<ProjectData> getProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        biz.futureware.mantis.rpc.soap.client.ProjectData[] projects = mc.mc_projects_get_user_accessible(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"));
        return Arrays.asList(projects).stream()
                .map((p) -> new ProjectData().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soap.baseUrl")));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mc.mc_issue_add(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"), issueData);
        IssueData createdIssueData = mc.mc_issue_get(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"), issueId);
        return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription()).withProject(new ProjectData()
                        .withId(createdIssueData.getProject().getId().intValue()).withName(createdIssueData.getProject().getName()));

    }

    public Set<Issue> getIssues() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        ProjectData project = app.soap().getProjects().iterator().next();
        biz.futureware.mantis.rpc.soap.client.IssueData[] issues = mc.mc_project_get_issues(app.getProperty("soap.adminLogin"), app.getProperty("soap.adminPassword"),
                BigInteger.valueOf(project.getId()),BigInteger.valueOf(1), BigInteger.valueOf(2));
        return Arrays.asList(issues).stream()
                .map((i) -> new Issue().withId(i.getId().intValue()).withSummary(i.getSummary()).withDescription(i.getDescription())
                        .withResolution(i.getResolution().getName())
                        .withStatus(i.getStatus().getName())).collect(Collectors.toSet());

    }
}
