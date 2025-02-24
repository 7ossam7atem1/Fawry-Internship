package com.global.example.springdaytwo.controller;

import com.global.example.springdaytwo.entities.ApplicationInfo;
import com.global.example.springdaytwo.services.ApplicationInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")  // Changed from /api/app-info
public class ApplicationInfoController {

    private final ApplicationInfoService applicationInfoService;

    public ApplicationInfoController(ApplicationInfoService applicationInfoService) {
        this.applicationInfoService = applicationInfoService;
    }

    @GetMapping("/info")  // This will make the full path /api/application/info
    public ApplicationInfo getApplicationInfo() {
        return applicationInfoService.getApplicationInfo();
    }

    @GetMapping("/info/build")
    public String getBuildNumber() {
        return applicationInfoService.getApplicationInfo().getBuildNumber();
    }

    @GetMapping("/info/git/commit")
    public String getGitHash() {
        return applicationInfoService.getApplicationInfo().getGitHash();
    }

    @GetMapping("/info/git/tag")
    public String getGitTag() {
        return applicationInfoService.getApplicationInfo().getGitTag();
    }

    @GetMapping("/info/version")
    public String getVersion() {
        return applicationInfoService.getApplicationInfo().getVersion();
    }
}