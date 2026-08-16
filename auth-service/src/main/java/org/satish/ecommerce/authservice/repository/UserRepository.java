package org.satish.ecommerce.authservice.repository;

import org.satish.ecommerce.authservice.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    User save(User user);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
