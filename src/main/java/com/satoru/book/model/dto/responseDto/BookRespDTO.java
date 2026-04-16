package com.satoru.book.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRespDTO {

    private Long bookId;

    private String title;

    private String authorName;

    private String isbn;

    private String synopsis;

    private String bookCover;

    private Boolean archived;

    private Boolean shareable;

    private List<FeedbackRespDTO> feedbacks;

    private List<BookTransactionHistoryRespDTO> transactionHistories;

}
