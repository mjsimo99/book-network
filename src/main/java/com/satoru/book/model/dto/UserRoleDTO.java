package com.satoru.book.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDTO {

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Role ID cannot be null")
    private Long roleId;

}
