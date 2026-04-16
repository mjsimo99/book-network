package com.satoru.book.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTransactionHistoryRespDTO {

    private Long historyId;

    private Boolean returned;

    private Boolean returnApproved;

    private Long bookId;

    private Long userId;

}
