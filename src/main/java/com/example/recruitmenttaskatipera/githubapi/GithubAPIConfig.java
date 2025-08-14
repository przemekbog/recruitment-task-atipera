package com.example.recruitmenttaskatipera.githubapi;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.github.api")
public class GithubAPIConfig {
    private String token;
}
