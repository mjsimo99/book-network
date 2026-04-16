package com.satoru.book.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {

    private Long feedbackId;

    @NotNull(message = "Rating cannot be null")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer note;

    @Size(max = 1000, message = "Comment must not exceed 1000 characters")
    private String comment;

    @NotNull(message = "Book ID cannot be null")
    private Long bookId;

}
