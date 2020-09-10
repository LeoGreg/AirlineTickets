package com.example.ticket.repository;


import com.example.ticket.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TimeTableRepository<T> extends JpaRepository<TimeTable, Long> {

    boolean existsById(long id);

    TimeTable save(TimeTable flight);

    TimeTable getByIdAndTicketPrice(long id, double price);

    boolean existsByStartingPlaceAndDestinationAndTicketPrice(String placeF, String placeTo, double price);

    TimeTable getByStartingPlaceAndDestinationAndTicketPriceAndTakeOff(String placeF, String placeTo, double price, long takeOff);



}


