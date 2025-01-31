package com.example.projetmindfit.Service;


import com.example.projetmindfit.Repository.CoachRepo;
import com.example.projetmindfit.Repository.MeditantRepo;
import com.example.projetmindfit.entity.Coach;
import com.example.projetmindfit.entity.Meditant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private CoachRepo coachRepo;

    @Autowired
    private MeditantRepo meditantRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Meditant meditant = meditantRepo.findByEmail(email)
                .orElse(null);

        if (meditant != null) {
            return meditant;
        }

        Coach coach = coachRepo.findByEmail(email)
                .orElse(null);

        if (coach != null) {
            return coach;
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }

}