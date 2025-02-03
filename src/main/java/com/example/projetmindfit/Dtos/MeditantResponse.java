package com.example.projetmindfit.Dtos;

import com.example.projetmindfit.entity.Etat;

public record MeditantResponse(Long id , String nom , String prenom, String email , int age , Etat etat ) {
}
