package com.example.projetmindfit.Service;


import com.example.projetmindfit.Repository.ExerciceRepo;

import com.example.projetmindfit.entity.Exercice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciceService {

    @Autowired
    private ExerciceRepo exerciceRepository;

    public List<Exercice> getExercicesByHumeur(String exerciceHumeur) {
        return exerciceRepository.findByExerciceHumeur(exerciceHumeur);
    }

    public Exercice saveExercice(Exercice exercice) {

        return exerciceRepository.save(exercice);
    }

}
