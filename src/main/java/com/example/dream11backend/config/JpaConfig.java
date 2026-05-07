package com.example.dream11backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(basePackages = "com.example.dream11backend.repository")
public class JpaConfig {

    /**
     * Provides the currently authenticated username for JPA auditing (@CreatedBy / @LastModifiedBy).
     * Falls back to "system" when no authentication context is present (e.g. during startup/data.sql).
     */
    @Bean
    @SuppressWarnings("null")
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
                return Optional.of("system");
            }
            String name = auth.getName();
            return Optional.of(name != null ? name : "system");
        };
    }
}
