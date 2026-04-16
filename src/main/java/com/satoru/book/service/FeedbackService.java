package com.satoru.book.service;

import com.satoru.book.model.dto.FeedbackDTO;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDTO> getAllFeedbacks();
    FeedbackDTO getFeedbackById(Long feedbackId);
    FeedbackDTO createFeedback(FeedbackDTO feedbackDTO);
    FeedbackDTO updateFeedback(Long feedbackId, FeedbackDTO feedbackDTO);
    void deleteFeedback(Long feedbackId);
}