package com.example.ticket.controller.chechBoolean;

import org.springframework.stereotype.Component;

@Component
public class TrueOrFalseToText {

    public  String isChargingDone(boolean okay) {
        if (okay) {
            return "ticket price 's properly been charged";
        } else return "there must've been some problem,transaction 's been called off";
    }

    public  String isRefundingDone(boolean okay) {
        if (okay) {
            return "you've successfully had your money back on your account";
        } else return "money can't be refunded";
    }
}
