package com.satoru.book.repository;

import com.satoru.book.model.entity.Role;
import com.satoru.book.model.enumuration.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
