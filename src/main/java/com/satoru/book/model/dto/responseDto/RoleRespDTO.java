package com.satoru.book.model.dto.responseDto;

import com.satoru.book.model.enumuration.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleRespDTO {

    private Long roleId;

    private RoleName name;

}
