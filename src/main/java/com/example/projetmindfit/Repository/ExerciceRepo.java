package com.example.projetmindfit.Repository;

import com.example.projetmindfit.entity.Exercice;
import com.example.projetmindfit.entity.Humeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExerciceRepo extends JpaRepository<Exercice, Long> {
    List<Exercice> findByExerciceHumeur(String exerciceHumeur);

}
