package com.satoru.book.model.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRespDTO {

    private Long feedbackId;

    private Integer note;

    private String comment;

    private Long bookId;

}
