package com.example.recruitmenttaskatipera.githubapi;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.github.api")
public class GithubAPIConfig {
    private String token;

    @PostConstruct
    public void validate() {
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("Github token is required but not set");
        }
    }
}
