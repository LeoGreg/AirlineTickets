package com.example.ticket.util.exception;

public class NotFoundFlightException extends Exception {

    public NotFoundFlightException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws NotFoundFlightException {
        if (ex) {
            throw new NotFoundFlightException(message);
        }
    }


}
