package com.example.projetmindfit;

import com.example.projetmindfit.Repository.CoachRepo;
import com.example.projetmindfit.entity.Coach;
import com.example.projetmindfit.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjetMindFitApplication {

    private final PasswordEncoder passwordEncoder;
    private final CoachRepo coachRepo;

    public static void main(String[] args) {
        SpringApplication.run(ProjetMindFitApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CoachRepo coachRepo ,
                          PasswordEncoder passwordEncoder) {
        return args -> {
            if (coachRepo.findByRole(Role.COACH).isPresent())
                return;
            Coach coach = new Coach("Coach@example.com", passwordEncoder.encode("password"));
            coachRepo.save(coach);
        };
    }

}
