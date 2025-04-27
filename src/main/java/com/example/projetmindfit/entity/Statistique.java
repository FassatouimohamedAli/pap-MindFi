package com.example.projetmindfit.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Historique")
public class Statistique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "statistique_exercices", // nouvelle table intermédiaire
            joinColumns = @JoinColumn(name = "statistique_id"),
            inverseJoinColumns = @JoinColumn(name = "exercice_id")
    )
    private List<Exercice> exercices;

    @ManyToOne
    @JoinColumn(name = "Meditant_id")
    private Meditant  meditant;

}
