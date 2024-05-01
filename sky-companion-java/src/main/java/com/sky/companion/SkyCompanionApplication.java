/**
 * The main application class for the Sky Companion project.
 * This class is responsible for starting the Spring Boot application and
 * configuring various features.
 * It is annotated with {@link SpringBootApplication} to enable Spring Boot
 * auto-configuration.
 * The {@link EnableWebSecurity} annotation enables web security configuration.
 * The {@link EnableJpaAuditing} annotation enables JPA auditing for entity
 * classes.
 * The {@link EnableScheduling} annotation enables scheduling of tasks.
 * The {@link UserDetailsServiceAutoConfiguration} is excluded to prevent
 * testing generated security password.
 */

package com.sky.companion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
@EnableWebSecurity
@EnableJpaAuditing
@EnableScheduling
public class SkyCompanionApplication {

    /**
     * entry point for java and this runs main class as a Spring context
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(SkyCompanionApplication.class, args);
    }

}
