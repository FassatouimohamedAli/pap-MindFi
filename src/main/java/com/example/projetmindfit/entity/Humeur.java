package com.example.projetmindfit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "humeur")
public class Humeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etatHumeur ;
    private String descriptionHummeur ;

    @ManyToMany
    @JoinTable(
            name="humeur_exercice",
            joinColumns = @JoinColumn(name = "humeur_id"),
            inverseJoinColumns = @JoinColumn(name = "exercice_id")
    )
    Set<Exercice> exercices ;



}
