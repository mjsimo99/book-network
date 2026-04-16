package com.satoru.book.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long bookId;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 255, message = "Title must be between 3 and 255 characters")
    private String title;

    @NotBlank(message = "Author name cannot be blank")
    @Size(min = 3, max = 100, message = "Author name must be between 3 and 100 characters")
    private String authorName;

    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @NotBlank(message = "Synopsis cannot be blank")
    @Size(min = 10, max = 2000, message = "Synopsis must be between 10 and 2000 characters")
    private String synopsis;

    private String bookCover;

    @NotNull(message = "Archived status cannot be null")
    private Boolean archived = false;

    @NotNull(message = "Shareable status cannot be null")
    private Boolean shareable = false;

}
