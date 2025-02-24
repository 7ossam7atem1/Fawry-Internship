package com.global.example.springdaytwo.services;

import com.global.example.springdaytwo.config.SmsConfig;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final SmsConfig smsConfig;

    public SmsService(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    public void sendSms(String to, String message) {
        if (!smsConfig.isEnabled()) {
            throw new IllegalStateException("SMS service is disabled");
        }

        System.out.println("Sending SMS via " + smsConfig.getProvider());
        System.out.println("From: " + smsConfig.getSenderId());
        System.out.println("To: " + to);
        System.out.println("Message: " + message);
        System.out.println("Using API Key: " + smsConfig.getApiKey());
        System.out.println("Endpoint: " + smsConfig.getEndpoint());
    }
}