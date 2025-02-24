package com.global.example.springdaytwo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String version;
    private Build build;
    private Git git;

    public static class Build {
        private String number;
        private String timestamp;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class Git {
        private String commit;
        private String tag;

        public String getCommit() {
            return commit;
        }

        public void setCommit(String commit) {
            this.commit = commit;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public Git getGit() {
        return git;
    }

    public void setGit(Git git) {
        this.git = git;
    }
}