package com.satoru.book.model.dto;

import com.satoru.book.model.enumuration.RoleName;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    private Long roleId;

    @NotNull(message = "Role name cannot be null")
    private RoleName name;

}
