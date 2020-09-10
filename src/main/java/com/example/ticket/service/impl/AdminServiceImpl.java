package com.example.ticket.service.impl;

import com.example.ticket.model.TimeTable;
import com.example.ticket.repository.TimeTableRepository;
import com.example.ticket.service.abst.AdminService;
import com.example.ticket.util.exception.DuplicateException;
import com.example.ticket.util.exception.NotFoundFlightException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TimeTableRepository flightRepository;


    @Override
    @Transactional
    public TimeTable save(TimeTable flights) throws DuplicateException {

            DuplicateException.check(flightRepository.existsByStartingPlaceAndDestinationAndTicketPrice(flights.getStartingPlace(), flights.getDestination(), flights.getTicketPrice()), "duplicate.flight");
            return flightRepository.save(flights);

    }

    @Override
    @Transactional
    public void update(TimeTable flights, long id) throws NotFoundFlightException {
        NotFoundFlightException.check(flightRepository.getByIdAndTicketPrice(id, flights.getTicketPrice()) == null, "wrong.id");
        TimeTable dbFlight = flightRepository.getByIdAndTicketPrice(id, flights.getTicketPrice());
        dbFlight.setStartingPlace(flights.getStartingPlace());
        dbFlight.setId(id);
        dbFlight.setLanding(flights.getLanding());
        dbFlight.setTakeOff(flights.getTakeOff());
        dbFlight.setDestination(flights.getDestination());
        dbFlight.setTicketPrice(flights.getTicketPrice());
    }

    @Override
    @Transactional
    public void delete(long id) throws NotFoundFlightException {
        NotFoundFlightException.check(!flightRepository.existsById(id), "wrong.id");
        flightRepository.deleteById(id);
    }

    @Override
    public List<TimeTable> getAll() {
            return flightRepository.findAll();

    }

}
