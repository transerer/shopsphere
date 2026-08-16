package org.satish.ecommerce.authservice.security;

import org.satish.ecommerce.authservice.domain.User;
import org.satish.ecommerce.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public AuthenticationService(
            UserRepository userRepository,
            PasswordService passwordService) {

        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public User authenticate(String username, String password) {

        // 1. Find user
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Invalid credentials"));

        // 2. Check account status
        if (!user.isEnabled()) {
            throw new RuntimeException("Account disabled");
        }

        if (user.isAccountLocked()) {
            throw new RuntimeException("Account locked");
        }

        if (user.isAccountExpired()) {
            throw new RuntimeException("Account expired");
        }

        if (user.isCredentialsExpired()) {
            throw new RuntimeException("Credentials expired");
        }

        // 3. Verify password
        if (!passwordService.matches(
                password,
                user.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }

        // 4. Authentication successful
        return user;
    }
}