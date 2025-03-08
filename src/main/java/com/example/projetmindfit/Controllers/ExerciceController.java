package com.example.projetmindfit.Controllers;


import com.example.projetmindfit.Service.ExerciceService;
import com.example.projetmindfit.entity.Exercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Coach/exercices")
public class ExerciceController {
    @Autowired
    private ExerciceService exerciceService;

    // Ajouter un exercice
    @PostMapping
    public Exercice ajouterExercice(@RequestBody Exercice exercice) {
        return exerciceService.ajouterExercice(exercice);
    }

    // Obtenir tous les exercices
    @GetMapping
    public List<Exercice> obtenirTousLesExercices() {
        return exerciceService.obtenirTousLesExercices();
    }

    // Obtenir un exercice par ID
    @GetMapping("/{id}")
    public ResponseEntity<Exercice> obtenirExerciceParId(@PathVariable Long id) {
        Optional<Exercice> exercice = exerciceService.obtenirExerciceParId(id);
        return exercice.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Modifier un exercice
    @PutMapping("/{id}")
    public ResponseEntity<Exercice> modifierExercice(@PathVariable Long id, @RequestBody Exercice exerciceDetails) {
        try {
            Exercice updatedExercice = exerciceService.modifierExercice(id, exerciceDetails);
            return ResponseEntity.ok(updatedExercice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un exercice
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerExercice(@PathVariable Long id) {
        exerciceService.supprimerExercice(id);
        return ResponseEntity.noContent().build();
    }

    // Obtenir les exercices par humeur
    @GetMapping("/humeur/{humeur}")
    public List<Exercice> obtenirExercicesParHumeur(@PathVariable String humeur) {
        return exerciceService.obtenirExercicesParHumeur(humeur);
    }

    @GetMapping("planifier/{humeur}")
    public List<Exercice> ajouterExercice(@PathVariable String humeur) {
        return exerciceService.planifierExercice(humeur);
    }


}
