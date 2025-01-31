package com.example.projetmindfit.Controllers;


import com.example.projetmindfit.Dtos.MeditantRequest;
import com.example.projetmindfit.Dtos.MeditantResponse;
import com.example.projetmindfit.Service.MeditantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class MeditantControlelr {

    @Autowired
    private MeditantService meditantService;

    @PostMapping("/register")
    public ResponseEntity<MeditantResponse> createCandidate(@RequestBody MeditantRequest meditantRequest) {
        try {

            return ResponseEntity.status(CREATED).body(meditantService.registre(meditantRequest));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();

        }
    }
}



