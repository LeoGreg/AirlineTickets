package com.example.ticket.configs;

import com.example.ticket.util.encoding.CustomEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class BasicAdminSec extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/airline/**")
                .httpBasic().and()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().authorizeRequests()
                .antMatchers("/airline/**").authenticated();

    }


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("ticket_admin").password("ticket_password").authorities("ADMIN_ROLE").and()
                .passwordEncoder(CustomEncoder.getInstance());
    }

}