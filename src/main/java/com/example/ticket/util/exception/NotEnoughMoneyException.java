package com.example.ticket.util.exception;

public class NotEnoughMoneyException extends Exception {

    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws NotEnoughMoneyException {
        if (ex) {
            throw new NotEnoughMoneyException(message);
        }
    }
}
