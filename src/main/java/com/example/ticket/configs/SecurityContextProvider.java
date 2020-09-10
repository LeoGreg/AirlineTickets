package com.example.ticket.configs;

import com.example.ticket.model.oauth.Authority;
import com.example.ticket.model.oauth.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class SecurityContextProvider {

    @Autowired
    private ResourceServerTokenServices resourceServerTokenServices;

    @Autowired
    private TokenProvider tokenProvider;


    @SuppressWarnings("unchecked")
    public Consumer getByAuthentication(OAuth2Authentication oAuth2Authentication) {

        Map<String, Object> additional = resourceServerTokenServices.readAccessToken(tokenProvider.getTokenValue(oAuth2Authentication)).getAdditionalInformation();

        Consumer consumer = new Consumer();
        consumer.setId((Integer) additional.get("id"));
        consumer.setUsername(additional.get("username").toString());
        consumer.setFirst_name(additional.get("first_name").toString());
        consumer.setLast_name(additional.get("last_name").toString());

        HashMap<Integer, String> roles = (HashMap<Integer, String>) additional.get("roles");
        Set<Authority> authorities = new HashSet<>();
        roles.forEach((key, value) -> authorities.add(new Authority(key, value)));
        consumer.setAuthorities(authorities);


        return consumer;
    }

    public String readTokenValue(OAuth2Authentication auth2Authentication) {
        return tokenProvider.getTokenValue(auth2Authentication);
    }
}