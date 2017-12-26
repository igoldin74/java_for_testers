package com.igoldin.qa.school.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GitHubTests {



    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("055685e7a17ee81cf62ebd0aea1072e9ed4974d7");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("igoldin74", "java_for_testers")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }


    }
}
