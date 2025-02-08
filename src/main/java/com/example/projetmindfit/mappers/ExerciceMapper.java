package com.example.projetmindfit.mappers;

import com.example.projetmindfit.Dtos.ExerciceDTO;
import com.example.projetmindfit.Dtos.HumeurDTO;
import com.example.projetmindfit.entity.Exercice;

public class ExerciceMapper {
    public static ExerciceDTO toDTO(Exercice exercice) {
        HumeurDTO humeurDTO = HumeurMapper.toDTO(exercice.getHumeur());
        return new ExerciceDTO(exercice.getId(), exercice.getNom(), exercice.getDescription(),
                exercice.getDuree(), exercice.getFrequence_recommandee(), humeurDTO);
    }
}
