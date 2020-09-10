package com.example.ticket.util.exception;

public class NoAvailableFlight extends Exception {

    public NoAvailableFlight(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws NoAvailableFlight {
        if (ex) {
            throw new NoAvailableFlight(message);
        }
    }

}
