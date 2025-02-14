package com.example.projetmindfit.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "exercice")
public class Exercice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom ;
    private String description ;
    private int duree;

    private String frequence_recommandee;
    // exercie humeur iwali ba3ed iverifi
    // l humeur de meditant par humeur de Exercice w y5arjlou les exercice hedheka
    //kenghata badel
    private String  exerciceHumeur;

}
