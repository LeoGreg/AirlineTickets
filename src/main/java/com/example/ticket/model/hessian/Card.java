package com.example.ticket.model.hessian;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;



@Data
public class Card {


    private int id;

    private String number;


    private String cvv;

    private double balance;

    private int consumer_id;

}
