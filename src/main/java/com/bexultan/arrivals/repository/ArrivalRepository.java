package com.bexultan.arrivals.repository;

import com.bexultan.arrivals.domain.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrivalRepository extends JpaRepository<Arrival, Long> {
}
