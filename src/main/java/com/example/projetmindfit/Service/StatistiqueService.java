package com.example.projetmindfit.Service;

import com.example.projetmindfit.Repository.ExerciceRepo;
import com.example.projetmindfit.Repository.MeditantRepo;
import com.example.projetmindfit.Repository.StatistiqueRepo;
import com.example.projetmindfit.entity.Exercice;
import com.example.projetmindfit.entity.Meditant;
import com.example.projetmindfit.entity.Statistique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatistiqueService {

    @Autowired
    private StatistiqueRepo statistiqueRepo;
    @Autowired
    private MeditantRepo   meditantRepo;

    @Autowired
    private ExerciceRepo exerciceRepo;


    public Statistique saveStatistique(String email, List<Exercice> exercices) {
        // 1. Chercher le méditant par email
        System.out.println("00000000");
        if (meditantRepo.findByEmail(email).isEmpty()) {
            throw new RuntimeException("Méditant non trouvé avec cet email !");
        }
        Meditant meditant = meditantRepo.findByEmail(email).get();

        // 2. Vérifier que tous les exercices existent dans la base
        for (Exercice exercice : exercices) {
            Optional<Exercice> existingExercice = exerciceRepo.findById(exercice.getId());
            if (existingExercice.isEmpty()) {
                throw new RuntimeException("L'exercice avec l'id " + exercice.getId() + " n'existe pas !");
            }
        }

        // 3. Créer une nouvelle statistique avec les exercices valides
        Statistique statistique = Statistique.builder()
                .meditant(meditant)
                .exercices(exercices)
                .build();

        // 4. Sauvegarder
        return statistiqueRepo.save(statistique);
    }

    // Récupérer les statistiques d'un utilisateur
    public List<Statistique> getStatistiquesByEmail(String email) {
        Meditant meditant = meditantRepo.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Méditant avec email " + email + " non trouvé !"));

        return statistiqueRepo.findByMeditant(meditant); // Assure-toi que cette méthode existe dans le repo
    }

    // Récupérer les exercices les plus effectués par un utilisateur
    public List<Exercice> getTopExercicesByEmail(String email) {
        Meditant meditant = meditantRepo.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Méditant avec email " + email + " non trouvé !"));

        // Récupérer les statistiques pour cet utilisateur
        List<Statistique> statistiques = statistiqueRepo.findByMeditant(meditant);

        // Calculer les exercices les plus effectués
        Map<Long, Integer> exerciceCountMap = new HashMap<>();
        for (Statistique statistique : statistiques) {
            for (Exercice exercice : statistique.getExercices()) {
                exerciceCountMap.put(exercice.getId(), exerciceCountMap.getOrDefault(exercice.getId(), 0) + 1);
            }
        }

        List<Exercice> topExercices = new ArrayList<>();
        exerciceCountMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) // Trier par fréquence
                .forEach(entry -> {
                    Optional<Exercice> exercice = exerciceRepo.findById(entry.getKey());
                    exercice.ifPresent(topExercices::add);
                });

        return topExercices;
    }


    // Méthode pour obtenir le nombre d'exercices effectués par un méditant
    public int getNombreExercicesParMeditant(String email) {
        // Chercher le méditant par email
        Meditant meditant = meditantRepo.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Méditant avec email " + email + " non trouvé !"));

        // Récupérer les statistiques du méditant
        List<Statistique> statistiques = statistiqueRepo.findByMeditant(meditant);

        // Calculer le nombre total d'exercices effectués
        int nombreExercices = 0;
        for (Statistique statistique : statistiques) {
            nombreExercices += statistique.getExercices().size();
        }

        return nombreExercices;
    }

}
