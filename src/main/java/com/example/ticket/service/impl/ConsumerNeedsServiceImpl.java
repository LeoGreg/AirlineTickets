package com.example.ticket.service.impl;


import com.example.ticket.controller.hessian.HessianController;
import com.example.ticket.model.Canceled;
import com.example.ticket.model.Reserve;
import com.example.ticket.model.TimeTable;
import com.example.ticket.model.oauth.Consumer;
import com.example.ticket.repository.CancelRepository;
import com.example.ticket.repository.ReserveRepository;
import com.example.ticket.repository.TimeTableRepository;
import com.example.ticket.service.abst.ConsumerNeedsService;
import com.example.ticket.util.exception.NoAvailableFlight;
import com.example.ticket.util.exception.NotFoundFlightException;
import com.example.ticket.util.exception.TooLateToCallOffException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ConsumerNeedsServiceImpl implements ConsumerNeedsService {

    @Autowired
    private CancelRepository cancelRepository;
    @Autowired
    private ReserveRepository reserveRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private TimeTableRepository timeTableRepository;
    @Autowired
    private HessianController hessianController;

    @Override
    @Transactional
    public boolean reserve(Reserve flight, Consumer consumer) throws NoAvailableFlight {
        NoAvailableFlight.check(!timeTableRepository.existsByStartingPlaceAndDestinationAndTicketPrice(flight.getStartingPlace(), flight.getDestination(), flight.getTicketPrice()), "no,available.ticket");
        boolean isDone = hessianController.charge(consumer.getId(), flight.getTicketPrice());
        reserveRepository.save(flight);
        return isDone;
    }


    @Override
    @Transactional
    public boolean call_off(long f_id, Consumer consumer) throws NotFoundFlightException, TooLateToCallOffException {
        Reserve reserveFlight = reserveRepository.getById(f_id);
        TimeTable timeTable = timeTableRepository.getByIdAndTicketPrice(f_id, reserveFlight.getTicketPrice());
        TooLateToCallOffException.check(timeTable.getTakeOff() <= System.currentTimeMillis() + 24 * 60 * 60 * 1000, "too.late.to.cancel");
        NotFoundFlightException.check(reserveFlight == null, "wrong.flight.id");
        boolean isDone = hessianController.refund(consumer.getId(), reserveFlight.getTicketPrice());
        Canceled canceled = new Canceled();
        canceled.setDestination(reserveFlight.getDestination());
        canceled.setStartingPlace(reserveFlight.getStartingPlace());
        canceled.setTicketPrice(reserveFlight.getTicketPrice());
        cancelRepository.save(canceled);
        reserveRepository.delete(reserveFlight);
        return isDone;
    }


    @Override
    public List<TimeTable> getAll() {
        List<TimeTable> info = timeTableRepository.findAll();
        return info;

    }

    @Override
    @Transactional
    public List<Reserve> search(Object... item) {
        String[] keys = {"startingPlace", "destination", "ticketPrice"};
        HashMap<String, Object> map = new HashMap();
        for (int i = 0; i < item.length; i++) {
            map.put(keys[i], item[i]);
        }
        for (Iterator<?> it = map.values().iterator();
             it.hasNext(); ) {
            if (it.next() == null) {
                it.remove();
            }
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Reserve> criteriaQuery = criteriaBuilder.createQuery(Reserve.class);
        Root<Reserve> movieRoot = criteriaQuery.from(Reserve.class);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            log.info("size {}", map.size());
            criteriaQuery.where(criteriaBuilder.like(movieRoot.get(entry.getKey()), (String) entry.getValue()));
        }
        TypedQuery<Reserve> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();

    }

}


