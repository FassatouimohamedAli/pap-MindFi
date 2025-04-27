package com.example.projetmindfit.Controllers;

import com.example.projetmindfit.Service.StatistiqueService;
import com.example.projetmindfit.Util.JwtUtil;
import com.example.projetmindfit.entity.Exercice;
import com.example.projetmindfit.entity.Humeur;
import com.example.projetmindfit.entity.Meditant;
import com.example.projetmindfit.entity.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meditant")
public class StatistiqueController {

    @Autowired
    private StatistiqueService statistiqueService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/statistique")
    public ResponseEntity<Object> savestat(
            @RequestHeader("Authorization") String tokenAuth,
            @RequestBody List<Exercice> exercices) {
        try {
            // Extraire l'email à partir du token JWT
            String email = jwtUtil.extractEmail(tokenAuth.replace("Bearer ", ""));
            System.out.println("Email extrait du token : " + email);
            Statistique s = statistiqueService.saveStatistique(email, exercices);
            return ResponseEntity.ok(s);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 404 si l'email n'existe pas
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 pour d'autres erreurs
        }

    }


    // Récupérer les statistiques pour un utilisateur spécifique
    @GetMapping("/statistique")
    public ResponseEntity<Object> getStatistiquesByEmail(@RequestHeader("Authorization") String tokenAuth) {
        try {
            String email = jwtUtil.extractEmail(tokenAuth.replace("Bearer ", ""));
            System.out.println("Email extrait du token : " + email);
            List<Statistique> statistiques = statistiqueService.getStatistiquesByEmail(email);
            if (statistiques.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune statistique trouvée pour cet utilisateur");
            }
            return ResponseEntity.ok(statistiques);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des statistiques");
        }
    }

    // Récupérer les exercices les plus effectués par un utilisateur
    @GetMapping("/statistique/top-exercices")
    public ResponseEntity<Object> getTopExercicesByEmail(@RequestHeader("Authorization") String tokenAuth) {
        try {

            String email = jwtUtil.extractEmail(tokenAuth.replace("Bearer ", ""));
            System.out.println("Email extrait du token : " + email);
            List<Exercice> topExercices = statistiqueService.getTopExercicesByEmail(email);
            if (topExercices.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun exercice trouvé pour cet utilisateur");
            }
            return ResponseEntity.ok(topExercices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des exercices");
        }
    }

    @GetMapping("/statistique/nombre-exercices")
    public ResponseEntity<Object> getNombreExercicesParMeditant(@RequestHeader("Authorization") String tokenAuth) {
        try {
            String email = jwtUtil.extractEmail(tokenAuth.replace("Bearer ", ""));
            System.out.println("Email extrait du token : " + email);
            int nombreExercices = statistiqueService.getNombreExercicesParMeditant(email);
            return ResponseEntity.ok(nombreExercices); // Retourne le nombre d'exercices
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération du nombre d'exercices");
        }
    }

}
