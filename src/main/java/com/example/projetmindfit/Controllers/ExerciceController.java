package com.example.projetmindfit.Controllers;


import com.example.projetmindfit.Dtos.ExerciceDTO;
import com.example.projetmindfit.Service.ExerciceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ExerciceController {
    @Autowired
    private ExerciceService exerciceService;

    @GetMapping("/api/exercices/by-mood")
    public List<ExerciceDTO> getExercisesByMood(@RequestParam String mood) {
        return exerciceService.getExercisesByMood(mood);
    }
}
