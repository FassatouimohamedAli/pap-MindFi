package com.example.projetmindfit.Repository;

import com.example.projetmindfit.entity.Coach;
import com.example.projetmindfit.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoachRepo extends JpaRepository<Coach, Long> {

    Optional<Coach> findByRole(Role role);
    Optional<Coach> findByEmail(String email);
}
