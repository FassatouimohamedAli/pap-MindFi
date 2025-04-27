package com.example.projetmindfit.Service;

import com.example.projetmindfit.Dtos.MapperMeditant;
import com.example.projetmindfit.Dtos.MeditantRequest;
import com.example.projetmindfit.Dtos.MeditantResponse;
import com.example.projetmindfit.Exceptions.MeditantEmailExistException;
import com.example.projetmindfit.Repository.MeditantRepo;
import com.example.projetmindfit.entity.Meditant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeditantService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MeditantRepo meditantRepo;
    @Autowired
    private MapperMeditant mapperMeditant ;

    public MeditantResponse registre(MeditantRequest meditantRequest){
if(meditantRepo.findByEmail(meditantRequest.email()).isPresent()){
    throw new MeditantEmailExistException("Meditant est déjà utilisé meme Email !");
}
Meditant m  = mapperMeditant.toMeditant(meditantRequest);
        return mapperMeditant.toResponse(meditantRepo.save(m));
    }

    public MeditantResponse getMeditant(String email){
        return mapperMeditant.toResponse(meditantRepo.findByEmail(email).get());
    }

    public MeditantResponse updateMeditant(String email , MeditantRequest meditantRequest){
        Optional<Meditant> optionalMeditant = meditantRepo.findByEmail(email);

        if (optionalMeditant.isEmpty()) {
            throw new RuntimeException("Méditant non trouvé avec cet email !");
        }

        Meditant meditantExist = optionalMeditant.get();

        if (meditantRequest.nom() != null) {
            meditantExist.setNom(meditantRequest.nom());
        }

        if (meditantRequest.prenom() != null) {
            meditantExist.setPrenom(meditantRequest.prenom());
        }

        if(meditantRequest.password() != null){
            meditantExist.setPassword(passwordEncoder.encode(meditantRequest.password()));
        }

        if(meditantRequest.etat() != null){
            meditantExist.setEtat(meditantRequest.etat());
        }

        return mapperMeditant.toResponse(meditantRepo.save(meditantExist));
    }



    public  void deleteProfil(MeditantRequest meditantRequest){
        meditantRepo.delete(mapperMeditant.toMeditant(meditantRequest));
    }
}
