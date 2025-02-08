package com.example.projetmindfit.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ExerciceDTO {
    private Long id;
    private String nom;
    private String description;
    private int duree;
    private String frequenceRecommandee;
    private HumeurDTO humeur;
}
