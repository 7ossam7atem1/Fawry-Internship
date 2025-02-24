package com.global.example.springdaytwo.projection;

public interface CustomerProjection {
    String getFirstName();
    String getLastName();
    String getEmail();

    default String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}