package com.example.ticket.configs.hessian;

import com.example.ticket.service.hessian.CardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class HessianConfig {


    @Bean
    public CardService cardService() {
        HessianProxyFactoryBean invoker = new HessianProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:8060/interconnect/cardService");
        invoker.setServiceInterface(CardService.class);
        invoker.setHessian2(true);
        invoker.setUsername("interconnect_user");
        invoker.setPassword("interconnect_password");
        invoker.afterPropertiesSet();
        return (CardService) invoker.getObject();
    }
}