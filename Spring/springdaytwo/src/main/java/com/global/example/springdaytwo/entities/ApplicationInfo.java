package com.global.example.springdaytwo.entities;

public class ApplicationInfo {
    private String buildNumber;
    private String gitHash;
    private String gitTag;
    private String buildTimestamp;
    private String version;

    // Default constructor
    public ApplicationInfo() {
    }

    // Constructor with all fields
    public ApplicationInfo(String buildNumber, String gitHash, String gitTag, String buildTimestamp, String version) {
        this.buildNumber = buildNumber;
        this.gitHash = gitHash;
        this.gitTag = gitTag;
        this.buildTimestamp = buildTimestamp;
        this.version = version;
    }


    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getGitHash() {
        return gitHash;
    }

    public void setGitHash(String gitHash) {
        this.gitHash = gitHash;
    }

    public String getGitTag() {
        return gitTag;
    }

    public void setGitTag(String gitTag) {
        this.gitTag = gitTag;
    }

    public String getBuildTimestamp() {
        return buildTimestamp;
    }

    public void setBuildTimestamp(String buildTimestamp) {
        this.buildTimestamp = buildTimestamp;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
                "buildNumber='" + buildNumber + '\'' +
                ", gitHash='" + gitHash + '\'' +
                ", gitTag='" + gitTag + '\'' +
                ", buildTimestamp='" + buildTimestamp + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}