package com.example.ticket.advice;


import com.example.ticket.util.exception.*;
import com.example.ticket.util.hessian.NoEnoughBalanceToFulfillException;
import com.example.ticket.util.hessian.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Log4j2
@ControllerAdvice
public class ExceptionToStatusConverter {


    @ResponseBody
    @ExceptionHandler(NoAvailableFlight.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String duplicate(NoAvailableFlight e) {
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    String duplicate(NotEnoughMoneyException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotFoundFlightException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String duplicate(NotFoundFlightException e) {
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicate(DuplicateException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(TooLateToCallOffException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicate(TooLateToCallOffException e) {
        return e.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String duplicate(NotFoundException e) {
        return e.getMessage();
    }



    @ResponseBody
    @ExceptionHandler(NoEnoughBalanceToFulfillException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String duplicate(NoEnoughBalanceToFulfillException e) {
        return e.getMessage();
    }


}