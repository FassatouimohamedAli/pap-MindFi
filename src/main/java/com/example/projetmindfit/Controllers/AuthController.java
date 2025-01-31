package com.example.projetmindfit.Controllers;

import com.example.projetmindfit.Dtos.JwtRequest;
import com.example.projetmindfit.Dtos.JwtResponse;
import com.example.projetmindfit.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        try {

            JwtResponse jwtResponse = authService.authenticate(jwtRequest);



            return ResponseEntity.ok(jwtResponse);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JwtResponse("Role_NAN",e.getMessage()));
        }
    }
}
