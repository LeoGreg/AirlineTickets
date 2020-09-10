package com.example.ticket.controller;


import com.example.ticket.configs.SecurityContextProvider;
import com.example.ticket.controller.chechBoolean.TrueOrFalseToText;
import com.example.ticket.model.Reserve;
import com.example.ticket.model.oauth.Consumer;
import com.example.ticket.service.abst.ConsumerNeedsService;
import com.example.ticket.util.exception.NoAvailableFlight;
import com.example.ticket.util.exception.NotEnoughMoneyException;
import com.example.ticket.util.exception.NotFoundFlightException;
import com.example.ticket.util.exception.TooLateToCallOffException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/api/airline")
public class ConsumerNeedsController {

    @Autowired
    private SecurityContextProvider provider;
    @Autowired
    private ConsumerNeedsService consumerNeedsService;
    @Autowired
    private TrueOrFalseToText trueOrFalseToText;

    @PostMapping("/reserve")
    public ResponseEntity reserve(@Valid @RequestBody Reserve flight, OAuth2Authentication o) throws NoAvailableFlight, NotEnoughMoneyException {
        Consumer consumer = provider.getByAuthentication(o);
        boolean isDone = consumerNeedsService.reserve(flight, consumer);
        return ResponseEntity.ok(trueOrFalseToText.isChargingDone(isDone));
    }

    @DeleteMapping("/call-off/{id}")
    public ResponseEntity call_off_reservation(@PathVariable int id, OAuth2Authentication o) throws NotFoundFlightException, TooLateToCallOffException {
        Consumer consumer = provider.getByAuthentication(o);
        boolean isDone = consumerNeedsService.call_off(id, consumer);
        return ResponseEntity.ok(trueOrFalseToText.isRefundingDone(isDone));
    }

    @GetMapping("/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(consumerNeedsService.getAll());
    }

    @GetMapping("/items")
    public ResponseEntity searchFor(@RequestParam(value = "startingPlace", required = false) String startingPlace,
                                @RequestParam(value = "destination", required = false) String destination,
                                @RequestParam(value = "ticketPrice", required = false) String ticketPrice) {
        log.info("s,d,t :{} {} {}", startingPlace, destination, ticketPrice);
        return ResponseEntity.ok(consumerNeedsService.search(startingPlace, destination, ticketPrice));
    }
}
