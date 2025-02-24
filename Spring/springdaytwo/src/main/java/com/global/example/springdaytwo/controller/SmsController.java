package com.global.example.springdaytwo.controller;

import com.global.example.springdaytwo.services.SmsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(SmsService smsService) {
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        smsService.sendSms(to, message);
        return "SMS sent successfully!";
    }
}