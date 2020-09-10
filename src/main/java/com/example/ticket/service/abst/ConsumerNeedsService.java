package com.example.ticket.service.abst;


import com.example.ticket.model.Reserve;
import com.example.ticket.model.TimeTable;
import com.example.ticket.model.oauth.Consumer;
import com.example.ticket.util.exception.NoAvailableFlight;
import com.example.ticket.util.exception.NotEnoughMoneyException;
import com.example.ticket.util.exception.NotFoundFlightException;
import com.example.ticket.util.exception.TooLateToCallOffException;

import javax.transaction.Transactional;
import java.util.List;

public interface ConsumerNeedsService {


    @Transactional
    boolean reserve(Reserve flight, Consumer consumer) throws NoAvailableFlight;

    @Transactional
    boolean call_off(long f_id, Consumer consumer) throws NotFoundFlightException, TooLateToCallOffException;

    List<TimeTable> getAll();

    @Transactional
    List<Reserve> search(Object... item);
}
