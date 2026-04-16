package com.satoru.book.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDTO {

    private Long userId;

    private String firstname;

    private String lastname;

    private String email;

    private LocalDate dateOfBirth;

    private Boolean accountLocked;

    private Boolean enabled;

}
