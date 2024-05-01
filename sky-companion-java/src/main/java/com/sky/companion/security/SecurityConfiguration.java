/**
 * This class is a configuration class for Spring Security in the project.
 * It manages CORS (Cross-Origin Resource Sharing), CSRF (Cross-Site Request
 * Forgery),
 * and endpoint whitelisting.
 * <p>
 * The main purpose of this class is to define the security settings for the
 * application,
 * including session management, authentication, and authorization.
 * <p>
 * It also enables JWT (JSON Web Token) token-based authorization by adding the
 * JWTAuthorizationFilter to the filter chain.
 * <p>
 * Additionally, it provides a PasswordEncoder bean to properly hash and secure
 * passwords
 * in the Spring Security application, ensuring the protection of user data from
 * unauthorized access.
 * <p>
 * This class is important to the project as it ensures the security of the
 * application,
 * protects sensitive user data, and allows for proper authentication and
 * authorization mechanisms.
 */

package com.sky.companion.security;

import com.sky.companion.security.jwt.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
public class SecurityConfiguration {

    /**
     * link {@link JWTAuthorizationFilter} used to enable jwt token based authorization
     */
    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    /**
     * This method override the http security to disable cors and csrf with whitelisting certain endpoints.
     * also adds {@link  JWTAuthorizationFilter} to run authorization filter on every request
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
                .authorizeRequests().antMatchers("/login", "/signup", "/forgot-password", "/reset-password", "/verify").permitAll()
                .anyRequest().authenticated()
                .and().addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Cors configuration to allow all domains to access Apis
     *
     * @return
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * By defining a PasswordEncoder bean and using it in your configuration,
     * you ensure that passwords are properly hashed and secured in your Spring Security application.
     * This helps protect user data from unauthorized access.
     *
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}