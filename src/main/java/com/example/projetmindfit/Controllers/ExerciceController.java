package com.example.projetmindfit.Controllers;


import com.example.projetmindfit.Dtos.ExerciceDTO;
import com.example.projetmindfit.Service.ExerciceService;
import com.example.projetmindfit.entity.Exercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercices")
public class ExerciceController {
    @Autowired
    private ExerciceService exerciceService;

    @GetMapping("/humeur/{humeurId}")
    public List<Exercice> getExercicesByHumeur(@PathVariable Long humeurId) {
        return exerciceService.getExercicesByHumeur(humeurId);
    }

    @PostMapping
    public Exercice createExercice(@RequestBody Exercice exercice) {
        return exerciceService.saveExercice(exercice);
    }
}
