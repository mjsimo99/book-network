package com.satoru.book.model.entity;

import com.satoru.book.model.identity.UserRoleId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User_Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {

    @EmbeddedId
    private UserRoleId id;

    @MapsId("user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("role")
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
