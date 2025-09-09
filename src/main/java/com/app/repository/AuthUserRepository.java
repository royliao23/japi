package com.app.repository;

import com.app.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer> {
    Optional<AuthUser> findByUsername(String username);
    Optional<AuthUser> findByEmail(String email);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);
    public boolean existsByUsernameAndIdNot(String username, Integer id);
    public boolean existsByEmailAndIdNot(String email, Integer id);}