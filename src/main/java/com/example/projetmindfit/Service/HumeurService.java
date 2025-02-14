package com.example.projetmindfit.Service;

import com.example.projetmindfit.Repository.HumeurRepo;
import com.example.projetmindfit.entity.Humeur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumeurService {

    @Autowired
    private HumeurRepo humeurRepo;
    public List<Humeur> getAllHumeurs() {
        return humeurRepo.findAll();
    }

    public Humeur getHumeurById(Long id) {
        return humeurRepo.findById(id).orElse(null);
    }

    public Humeur saveHumeur(Humeur humeur) {
        return humeurRepo.save(humeur);
    }


}
