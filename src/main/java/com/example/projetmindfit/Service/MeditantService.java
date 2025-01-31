package com.example.projetmindfit.Service;

import com.example.projetmindfit.Dtos.MapperMeditant;
import com.example.projetmindfit.Dtos.MeditantRequest;
import com.example.projetmindfit.Dtos.MeditantResponse;
import com.example.projetmindfit.Exceptions.MeditantEmailExistException;
import com.example.projetmindfit.Repository.MeditantRepo;
import com.example.projetmindfit.entity.Meditant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeditantService {

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
}
