package com.example.ticket.repository;

import com.example.ticket.model.Canceled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelRepository extends JpaRepository<Canceled,Integer> {
}
