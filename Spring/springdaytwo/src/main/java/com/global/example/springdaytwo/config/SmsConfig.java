package com.global.example.springdaytwo.config;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "sms")
@Validated
public class SmsConfig {
    @NotNull
    private String provider;
    @NotNull
    private String apiKey;
    private String senderId;
    private boolean enabled;
    private String endpoint;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public String toString() {
        return "SmsConfig{" +
                "provider='" + provider + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", senderId='" + senderId + '\'' +
                ", enabled=" + enabled +
                ", endpoint='" + endpoint + '\'' +
                '}';
    }
}