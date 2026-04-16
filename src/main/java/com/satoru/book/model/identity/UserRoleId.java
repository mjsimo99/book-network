package com.satoru.book.model.identity;

import com.satoru.book.model.entity.Role;
import com.satoru.book.model.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleId implements Serializable {
    //Composite Primary Key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
