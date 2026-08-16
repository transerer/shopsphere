package org.satish.ecommerce.authservice.controller;

public record LoginRequest(
        String username,
        String password
) {
}