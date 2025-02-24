package com.global.example.springdaytwo.projection;


public class CustomerNameDTO {
    private final String fullName;

    public CustomerNameDTO(String fullName) {
        this.fullName = fullName;
    }


    public String getFullName() {
        return fullName;
    }
}