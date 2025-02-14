package com.example.projetmindfit.Controllers;


import com.example.projetmindfit.Service.ExerciceService;
import com.example.projetmindfit.entity.Exercice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Coach/exercices")
public class ExerciceController {
    @Autowired
    private ExerciceService exerciceService;

    @GetMapping("/humeur/{exerciceHumeur}")
    public List<Exercice> getExercicesByHumeur(@PathVariable String exerciceHumeur) {
        return exerciceService.getExercicesByHumeur(exerciceHumeur);
    }

    @PostMapping
    public Exercice createExercice(@RequestBody Exercice exercice) {
        return exerciceService.saveExercice(exercice);
    }
}
