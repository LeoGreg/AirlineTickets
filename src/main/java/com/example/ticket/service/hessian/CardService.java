package com.example.ticket.service.hessian;

import com.example.ticket.util.hessian.NoEnoughBalanceToFulfillException;
import com.example.ticket.util.hessian.NotFoundException;

import javax.transaction.Transactional;

public interface CardService {

    @Transactional
    void charge(int id,double sum) throws NotFoundException, NoEnoughBalanceToFulfillException;

    @Transactional
    void refund(int id,double sum) throws NotFoundException, NoEnoughBalanceToFulfillException;
}
