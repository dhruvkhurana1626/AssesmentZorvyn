package com.example.AssesmentZorvyn.configuration;

import com.example.AssesmentZorvyn.dao.UserDao;
import com.example.AssesmentZorvyn.enums.Role;
import com.example.AssesmentZorvyn.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner createAdmin() {
        return args -> {

            if (!userDao.existsByEmail("SuperAdmin@Zorvyn.com")) {

                User admin = User.builder()
                        .name("Super Admin")
                        .email("SuperAdmin@Zorvyn.com")
                        .password(passwordEncoder.encode("ZoryvnBoss@123"))
                        .role(Role.ADMIN)
                        .createdAt(LocalDateTime.now())
                        .active(true)
                        .build();

                userDao.save(admin);
            }
        };
    }
}