package com.example.blackbell_user.config;

import com.example.blackbell_user.security.filter.AuthenticationFilter;
import com.example.blackbell_user.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final ObjectMapper objectMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    private final AccountService accountService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/actuator/**").permitAll()
            .and()
                .authorizeRequests().antMatchers("/**")
                .hasIpAddress("192.168.219.100")
            .and()
                .addFilter(authenticationFilter());

        return http.build();
    }
    @Bean
    protected AuthenticationFilter authenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(objectMapper, env, accountService);
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }

    @Bean
    protected AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(accountService);

        return new ProviderManager(daoAuthenticationProvider);
    }
}
