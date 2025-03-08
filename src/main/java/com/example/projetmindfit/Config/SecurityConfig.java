package com.example.projetmindfit.Config;

import com.example.projetmindfit.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter ;
    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors((Customizer.withDefaults()))
                .authorizeHttpRequests(request -> request.requestMatchers("/authenticate","/register","/Coach/exercices/planifier/**").permitAll()
                                .requestMatchers("/Coach/**").hasAnyAuthority("ROLE_COACH")
                                .requestMatchers("/meditant/**").hasAnyAuthority("ROLE_MEDITANT")
                                .anyRequest().authenticated()
                        //filtre mte3eek eli bech tamel biihom
                        // l authorizations wl authen li 3mlthom nty (configurations)
                ) .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authManager(UserDetailsService detailsService) {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setPasswordEncoder(passwordEncoder());
        daoProvider.setUserDetailsService(detailsService);
        return new ProviderManager(daoProvider);
    }
}
