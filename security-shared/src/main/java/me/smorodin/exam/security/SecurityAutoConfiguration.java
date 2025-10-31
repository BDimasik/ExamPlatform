package me.smorodin.exam.security;

import lombok.extern.slf4j.Slf4j;
import me.smorodin.exam.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@ConditionalOnClass(JwtUtil.class)
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "app.security.jwt", name = "enabled", havingValue = "true", matchIfMissing = true)
@Slf4j
public class SecurityAutoConfiguration {

    public SecurityAutoConfiguration() {
        log.info("=== SecurityAutoConfiguration LOADED ===");
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtUtil jwtUtil(JwtProperties properties) {
        return new JwtUtil(properties.getSecret(), properties.getExpiration());
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil) {
        return new JwtAuthenticationFilter(jwtUtil);
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityFilterChain securityFilterChainAutoConfigured(HttpSecurity http, JwtAuthenticationFilter jwtFilter) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}