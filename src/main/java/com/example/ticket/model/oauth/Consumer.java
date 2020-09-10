package com.example.ticket.model.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Data
public class Consumer {

    private int id;

    private String first_name;

    private String last_name;

    private String username;//gmail


    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "consumer_authority",
            joinColumns = @JoinColumn(name = "consumer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<Authority> authorities;



}

