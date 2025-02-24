package com.global.example.springdaytwo.projection;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerFullNameProjection {
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getFullName();
}