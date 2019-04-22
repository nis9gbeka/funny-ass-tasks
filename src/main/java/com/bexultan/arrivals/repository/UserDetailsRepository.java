package com.bexultan.arrivals.repository;

import com.bexultan.arrivals.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
