package com.example.projetmindfit.Service;


import com.example.projetmindfit.Repository.ExerciceRepo;

import com.example.projetmindfit.entity.Exercice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciceService {

    @Autowired
    private ExerciceRepo exerciceRepository;

    // Ajouter un nouvel exercice
    public Exercice ajouterExercice(Exercice exercice) {
        return exerciceRepository.save(exercice);
    }

    // Obtenir tous les exercices
    public List<Exercice> obtenirTousLesExercices() {
        return exerciceRepository.findAll();
    }

    // Obtenir un exercice par ID
    public Optional<Exercice> obtenirExerciceParId(Long id) {
        return exerciceRepository.findById(id);
    }

    // Supprimer un exercice par ID
    public void supprimerExercice(Long id) {
        exerciceRepository.deleteById(id);
    }

    // Modifier un exercice
    public Exercice modifierExercice(Long id, Exercice exerciceDetails) {
        return exerciceRepository.findById(id).map(exercice -> {
            exercice.setNom(exerciceDetails.getNom());
            exercice.setDescription(exerciceDetails.getDescription());
            exercice.setDuree(exerciceDetails.getDuree());
            exercice.setFrequence_recommandee(exerciceDetails.getFrequence_recommandee());
            exercice.setExerciceHumeur(exerciceDetails.getExerciceHumeur());
            return exerciceRepository.save(exercice);
        }).orElseThrow(() -> new RuntimeException("Exercice non trouvé !"));
    }

    // Obtenir les exercices par humeur
    public List<Exercice> obtenirExercicesParHumeur(String humeur) {
        return exerciceRepository.findByExerciceHumeur(humeur);
    }


    public List<Exercice>  planifierExercice(String humeur) {

        List<Exercice> planifExercice = new ArrayList<>();
        List<Exercice> exerciceHumeur = exerciceRepository.findByExerciceHumeur(humeur) ;
        Collections.shuffle(exerciceHumeur);
        int  n = 2 ;
        //sublist view donc lezm nzidou addall ( l items eli 5tarnehom random mel exercices
        planifExercice.addAll(exerciceHumeur.subList(0, Math.min(n, exerciceHumeur.size())));

return planifExercice;

    }
}
