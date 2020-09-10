package com.example.ticket.controller.hessian;

import com.example.ticket.service.hessian.CardService;
import com.example.ticket.util.hessian.NoEnoughBalanceToFulfillException;
import com.example.ticket.util.hessian.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hessian")
public class HessianController {


    @Autowired
    private CardService cardService;


    @PostMapping("/charge")
    public boolean charge(@RequestParam int id,@RequestBody double sum) {
        try {
            cardService.charge(id,sum);
            return true;
        } catch (NotFoundException | NoEnoughBalanceToFulfillException e) {
            return false;

        }
    }

    @PostMapping("/refund")
    public boolean refund(@RequestParam int id,@RequestParam double sum) {
        try {
            cardService.refund(id,sum);
            return true;
        } catch (NotFoundException | NoEnoughBalanceToFulfillException e) {
            return false;

        }
    }
}
