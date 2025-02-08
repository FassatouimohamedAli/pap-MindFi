package com.example.projetmindfit.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class HumeurDTO {
    private Long id;
    private String etatHumeur;
    private String descriptionHummeur;
}
