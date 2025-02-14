package com.example.projetmindfit.Repository;

import com.example.projetmindfit.entity.Coach;
import com.example.projetmindfit.entity.Meditant;
import com.example.projetmindfit.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MeditantRepo extends JpaRepository<Meditant, Long> {
    Optional<Meditant> findByRole(Role role);
    Optional<Meditant> findByEmail(String email);
}
