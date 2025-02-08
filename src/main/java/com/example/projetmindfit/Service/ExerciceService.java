package com.example.projetmindfit.Service;

import com.example.projetmindfit.Dtos.ExerciceDTO;
import com.example.projetmindfit.Repository.ExerciceRepo;
import com.example.projetmindfit.Repository.HumeurRepo;
import com.example.projetmindfit.entity.Exercice;
import com.example.projetmindfit.entity.Humeur;
import com.example.projetmindfit.mappers.ExerciceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciceService {

    @Autowired
    private ExerciceRepo exerciceRepository;

    @Autowired
    private HumeurRepo humeurRepository;

    public List<ExerciceDTO> getExercisesByMood(String moodName) {
        Humeur humeur = humeurRepository.findAll().stream()
                .filter(h -> h.getEtatHumeur().equalsIgnoreCase(moodName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Mood not found"));

        List<Exercice> exercices = exerciceRepository.findByHumeur(humeur);

        return exercices.stream()
                .map(ExerciceMapper::toDTO)
                .collect(Collectors.toList());
    }

}
