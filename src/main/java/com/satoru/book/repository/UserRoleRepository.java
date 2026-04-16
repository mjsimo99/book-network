package com.satoru.book.repository;

import com.satoru.book.model.entity.Role;
import com.satoru.book.model.entity.User;
import com.satoru.book.model.entity.UserRole;
import com.satoru.book.model.identity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
    List<UserRole> findByUser(User user);
    List<UserRole> findByRole(Role role);
}
