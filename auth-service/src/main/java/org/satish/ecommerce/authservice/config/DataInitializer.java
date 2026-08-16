package org.satish.ecommerce.authservice.config;

import org.satish.ecommerce.authservice.domain.User;
import org.satish.ecommerce.authservice.repository.UserRepository;
import org.satish.ecommerce.authservice.security.PasswordService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeUsers(
            UserRepository userRepository,
            PasswordService passwordService) {

        return args -> {

            User satish = new User();

            satish.setUsername("satish");

            satish.setPassword(
                    passwordService.hash("password123")
            );

            satish.setRoles(Set.of("USER"));

            satish.setEnabled(true);
            satish.setAccountLocked(false);
            satish.setAccountExpired(false);
            satish.setCredentialsExpired(false);

            userRepository.save(satish);


            User admin = new User();

            admin.setUsername("admin");

            admin.setPassword(
                    passwordService.hash("admin123")
            );

            admin.setRoles(Set.of("ADMIN", "USER"));

            admin.setEnabled(true);
            admin.setAccountLocked(false);
            admin.setAccountExpired(false);
            admin.setCredentialsExpired(false);

            userRepository.save(admin);
        };
    }
}