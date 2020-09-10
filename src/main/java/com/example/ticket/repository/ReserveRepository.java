package com.example.ticket.repository;

import com.example.ticket.model.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Integer> {
    Reserve getById(long id);
}
