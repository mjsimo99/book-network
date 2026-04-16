package com.satoru.book.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTransactionHistoryDTO {

    private Long historyId;

    @NotNull(message = "Returned status cannot be null")
    private Boolean returned = false;

    @NotNull(message = "Return approved status cannot be null")
    private Boolean returnApproved = false;

    @NotNull(message = "Book ID cannot be null")
    private Long bookId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

}
