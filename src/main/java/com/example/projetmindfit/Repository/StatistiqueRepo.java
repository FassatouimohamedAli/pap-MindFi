package com.example.projetmindfit.Repository;

import com.example.projetmindfit.entity.Meditant;
import com.example.projetmindfit.entity.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatistiqueRepo extends JpaRepository<Statistique, Integer> {
    List<Statistique> findByMeditant(Meditant meditant);
}
