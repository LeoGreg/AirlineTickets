package com.example.ticket;

import com.example.ticket.util.encoding.Md5Encoder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@SpringBootApplication
public class TicketApplication {

    @Value("${spring.token.datasource.url}")
    private String url;

    @Value("${spring.token.datasource.username}")
    private String username;

    @Value("${spring.token.datasource.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(TicketApplication.class, args);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Md5Encoder();
    }

    @Bean
    public TokenStore tokenStore() {
        DataSource dataSource = DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .build();
        return new JdbcTokenStore(dataSource);
    }
}
