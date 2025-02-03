package com.example.projetmindfit.Repository;

import com.example.projetmindfit.entity.Exercice;
import com.example.projetmindfit.entity.Humeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
@Repository
public interface HumeurRepo extends JpaRepository<Humeur, Long> {
    List<Exercice> findByEtatHumeur(String etatHumeur);
}
