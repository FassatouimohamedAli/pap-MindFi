package com.example.projetmindfit.Service;

import com.example.projetmindfit.Dtos.JwtRequest;
import com.example.projetmindfit.Dtos.JwtResponse;
import com.example.projetmindfit.Util.JwtUtil;
import com.example.projetmindfit.entity.Coach;
import com.example.projetmindfit.entity.Meditant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    @Autowired
    private JwtUtil jwtUtil;

    public JwtResponse authenticate(JwtRequest jwtRequest) {
        try {
            // Authentification via le gestionnaire d'authentification
            System.out.println("aaaaaaaaaaa");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.email(),
                            jwtRequest.password()
                    )
            );

            //System.out.println("bbbb");
            // Générer le token JWT
            String token = jwtUtil.generateToken(jwtRequest.email());

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.email());
            // Déterminer le rôle de l'utilisateur
            String role = determineRole(userDetails);
            // Retourner la réponse avec le token et les informations de l'utilisateur
            return new JwtResponse(role, token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }

    private String determineRole(UserDetails userDetails) {
        if (userDetails instanceof Coach coach) {
            return coach.getRole().name() ;
        } else if (userDetails instanceof Meditant  meditant ) {
            return meditant.getRole().name();
        }
        throw new RuntimeException("Impossible de déterminer le rôle de l'utilisateur.");
    }

}
