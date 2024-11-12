package com.techgatha.auth.AuthenticationService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    @Autowired
    private UserInfoDetailsDervice userInfoDetailsDervice;

//    @Bean
//    public UserInfoDetailsDervice userInfoDetailsDervice(){
//        return new UserInfoDetailsDervice();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // disable iframe to view h2-console
        http.headers(headerCustomizer-> headerCustomizer.frameOptions(frameOptionsCustomixer-> frameOptionsCustomixer.disable()));

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authorizationManagerRequestMatcherRegistry->
                        authorizationManagerRequestMatcherRegistry
                                .requestMatchers(
                                        "/h2-console/**",
                                        "/auth/user/**",
                                        "/auth/**")
                                .permitAll()
                                .anyRequest().authenticated())
                .build();

    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        //System.out.println(userInfoDetailsDervice);
        authenticationProvider.setUserDetailsService(userInfoDetailsDervice);
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
