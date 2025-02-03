package com.example.projetmindfit.Dtos;

import com.example.projetmindfit.entity.Etat;

public record MeditantRequest(String nom, String prenom, String email , String password , int age , Etat etat ) {
}
