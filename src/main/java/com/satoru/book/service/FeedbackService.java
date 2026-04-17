package com.satoru.book.service;

import com.satoru.book.model.dto.FeedbackDTO;
import com.satoru.book.model.dto.responseDto.FeedbackRespDTO;

import java.util.List;

public interface FeedbackService {
    List<FeedbackRespDTO> getAllFeedbacks();
    FeedbackRespDTO getFeedbackById(Long feedbackId);
    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);
    FeedbackDTO updateFeedback(Long feedbackId, FeedbackDTO feedbackDTO);
    void deleteFeedback(Long feedbackId);
}