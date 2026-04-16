package com.satoru.book.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {

    private Long tokenId;

    @NotBlank(message = "Token cannot be blank")
    private String token;

    @NotNull(message = "Created at cannot be null")
    @FutureOrPresent(message = "Created at must be in the present or future")
    private LocalDateTime createdAt;

    @NotNull(message = "Expires at cannot be null")
    @Future(message = "Expires at must be in the future")
    private LocalDateTime expiresAt;

    private LocalDateTime validatedAt;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

}
