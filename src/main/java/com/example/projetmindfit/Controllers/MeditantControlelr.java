package com.example.projetmindfit.Controllers;


import com.example.projetmindfit.Dtos.MeditantRequest;
import com.example.projetmindfit.Dtos.MeditantResponse;
import com.example.projetmindfit.Service.MeditantService;

import com.example.projetmindfit.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class MeditantControlelr {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MeditantService meditantService;

    @PostMapping("/register")
    public ResponseEntity<MeditantResponse> createCandidate(@RequestBody MeditantRequest meditantRequest) {
        try {

            return ResponseEntity.status(CREATED).body(meditantService.registre(meditantRequest));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }
    }

    @GetMapping("/meditant/profil")
    public ResponseEntity<MeditantResponse> getMeditantProfil( @RequestHeader("Authorization") String tokenAuth) {
       try {
           String email = jwtUtil.extractEmail(tokenAuth.replace("Bearer ", ""));

           MeditantResponse getMeditantprofil = meditantService.getMeditant(email);
           return ResponseEntity.ok(getMeditantprofil);
       }catch(Exception e) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }
    }

    @PutMapping("/meditant/modiferProfil")
    public ResponseEntity<MeditantResponse> modiferProfil(
            @RequestHeader("Authorization") String tokenAuth, // Récupérer le token du header
            @RequestBody MeditantRequest meditantRequest) {
        try {
            // Extraire l'email à partir du token JWT
            String email = jwtUtil.extractEmail(tokenAuth.replace("Bearer ", ""));

            MeditantResponse updatedMeditant = meditantService.updateMeditant(email, meditantRequest);
            return ResponseEntity.ok(updatedMeditant);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 si l'email n'existe pas
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 pour d'autres erreurs
        }
    }
}



