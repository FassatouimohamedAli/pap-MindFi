package com.example.projetmindfit.Controllers;

import com.example.projetmindfit.Service.HumeurService;
import com.example.projetmindfit.entity.Humeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/humeurs")
public class HumeurController {
    @Autowired
    private HumeurService humeurService;

    @GetMapping
    public List<Humeur> getAllHumeurs() {
        return humeurService.getAllHumeurs();
    }

    @PostMapping
    public Humeur createHumeur(@RequestBody Humeur humeur) {
        return humeurService.saveHumeur(humeur);
    }
}
