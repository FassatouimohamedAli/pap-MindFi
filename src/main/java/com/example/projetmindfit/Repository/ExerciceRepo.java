package com.example.projetmindfit.Repository;

import com.example.projetmindfit.entity.Exercice;
import com.example.projetmindfit.entity.Humeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciceRepo extends JpaRepository<Exercice, Long> {
    List<Exercice> findByExerciceHumeur(String humeur);
//Optional<Exercice> findByExerciceId(Long id);
}
