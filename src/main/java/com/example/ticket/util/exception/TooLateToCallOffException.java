package com.example.ticket.util.exception;

public class TooLateToCallOffException extends Exception {

    public TooLateToCallOffException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws TooLateToCallOffException {
        if (ex) {
            throw new TooLateToCallOffException(message);
        }
    }

}
