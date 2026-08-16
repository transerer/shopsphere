package org.satish.ecommerce.authservice.controller;

import org.satish.ecommerce.authservice.domain.User;
import org.satish.ecommerce.authservice.security.AuthenticationService;
import org.satish.ecommerce.authservice.security.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthController(
            AuthenticationService authenticationService,
            JwtService jwtService) {

        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        User user = authenticationService.authenticate(
                request.username(),
                request.password()
        );

        return jwtService.generateToken(user);
    }
}