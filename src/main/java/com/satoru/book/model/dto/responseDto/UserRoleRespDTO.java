package com.satoru.book.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRespDTO {

    private Long userId;

    private Long roleId;

    private String userName;

    private String roleName;

}
