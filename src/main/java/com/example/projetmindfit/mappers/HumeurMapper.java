package com.example.projetmindfit.mappers;

import com.example.projetmindfit.Dtos.HumeurDTO;
import com.example.projetmindfit.entity.Humeur;

public class HumeurMapper {
    public static HumeurDTO toDTO(Humeur humeur) {
        return new HumeurDTO(humeur.getId(), humeur.getEtatHumeur(), humeur.getDescriptionHummeur());
    }
}
