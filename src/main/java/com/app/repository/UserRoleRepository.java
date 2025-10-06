package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    // Find by user_id (equivalent to your FastAPI get single user role)
    @Query("SELECT ur FROM UserRole ur WHERE ur.userId = :userId")
    Optional<UserRole> findByUserId(@Param("userId") Long userId);

    // Check if exists by user_id
    boolean existsByUserId(Long userId);

    // Find all user roles ordered by id
    List<UserRole> findAllByOrderById();
}
