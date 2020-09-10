package com.example.ticket.service.abst;



import com.example.ticket.model.TimeTable;
import com.example.ticket.util.exception.DuplicateException;
import com.example.ticket.util.exception.NotFoundFlightException;

import javax.transaction.Transactional;
import java.util.List;

public interface AdminService {

    @Transactional
    TimeTable save(TimeTable flights) throws DuplicateException;

    @Transactional
    void update(TimeTable flights, long id) throws NotFoundFlightException;

    @Transactional
    void delete(long id) throws NotFoundFlightException;

    List<TimeTable> getAll();
}
