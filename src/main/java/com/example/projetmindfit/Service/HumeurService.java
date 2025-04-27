package com.example.projetmindfit.Service;

import com.example.projetmindfit.Repository.HumeurRepo;
import com.example.projetmindfit.Repository.MeditantRepo;
import com.example.projetmindfit.entity.Humeur;
import com.example.projetmindfit.entity.Meditant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumeurService {

    @Autowired
    private HumeurRepo humeurRepo;
    @Autowired
    private MeditantRepo    meditantRepo;
    public List<Humeur> getAllHumeurs() {
        return humeurRepo.findAll();
    }

    public Humeur getHumeurById(Long id) {
        return humeurRepo.findById(id).orElse(null);
    }

    public Humeur saveHumeur(Humeur humeur, String email) {
        if (meditantRepo.findByEmail(email).isEmpty()){
            throw new RuntimeException("Méditant non trouvé avec cet email !");
        }
        humeur.setMeditant(meditantRepo.findByEmail(email).get());

        return humeurRepo.save(humeur);
    }

    public List<Humeur> getAllHumeursByMeditant(String email) {
        // Chercher le méditant par email
        Meditant meditant = meditantRepo.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Méditant avec l'email " + email + " non trouvé"));

        // Récupérer toutes les humeurs du méditant
        return humeurRepo.findByMeditant(meditant);
    }


}
