package com.example.projetmindfit.Controllers;

import com.example.projetmindfit.Dtos.MeditantResponse;
import com.example.projetmindfit.Service.HumeurService;
import com.example.projetmindfit.Service.MeditantService;
import com.example.projetmindfit.Util.JwtUtil;
import com.example.projetmindfit.entity.Humeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class HumeurController {
    @Autowired
    private HumeurService humeurService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MeditantService meditantService;

    @GetMapping("/humeurs")
    public List<Humeur> getAllHumeurs() {
        return humeurService.getAllHumeurs();
    }

    @PostMapping("meditant/humeurs")
    public ResponseEntity<Object> createHumeur(@RequestBody Humeur humeur ,
                                               @RequestHeader("Authorization") String tokenAuth) {
        try {
            // Extraire l'email à partir du token JWT
            String email = jwtUtil.extractEmail(tokenAuth.replace("Bearer ", ""));
            System.out.println("Email extrait du token : " + email);
            Humeur h = humeurService.saveHumeur(humeur,email);
            return ResponseEntity.ok(h);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 si l'email n'existe pas
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 pour d'autres erreurs
        }
    }


}
