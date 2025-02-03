package com.example.projetmindfit.Dtos;

import com.example.projetmindfit.entity.Etat;
import com.example.projetmindfit.entity.Meditant;
import com.example.projetmindfit.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.projetmindfit.entity.Etat.SEDENTAIRE;
import static com.example.projetmindfit.entity.Role.MEDITANT;

@Service
public class MapperMeditant {
    @Autowired
    private PasswordEncoder passwordEncoder;

    public MeditantResponse toResponse(Meditant meditant) {
        return new MeditantResponse(
                meditant.getId(),
                meditant.getNom(),
                meditant.getPrenom(),
                meditant.getEmail(),
                meditant.getAge(),
                meditant.getEtat()

        );
    }



    public Meditant toMeditant(MeditantRequest meditantRequest) {
        return Meditant.builder()
                .nom(meditantRequest.nom())
                .prenom(meditantRequest.prenom())
                .email(meditantRequest.email())
                .password(passwordEncoder.encode(meditantRequest.password()))
                .role(MEDITANT)
                .age(meditantRequest.age())
                .etat(meditantRequest.etat())
                .build();
    }
}
