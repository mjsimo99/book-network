package com.satoru.book.controller;

import com.satoru.book.model.dto.FeedbackDTO;
import com.satoru.book.model.dto.responseDto.FeedbackRespDTO;
import com.satoru.book.service.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public ResponseEntity<List<FeedbackRespDTO>> getAllFeedbacks() {
        List<FeedbackRespDTO> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackRespDTO> getFeedbackById(@PathVariable("id") Long feedbackId) {
        FeedbackRespDTO feedbackDTO = feedbackService.getFeedbackById(feedbackId);
        return new ResponseEntity<>(feedbackDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FeedbackDTO> createFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO createdFeedbackDTO = feedbackService.createFeedback(feedbackDTO);
        return new ResponseEntity<>(createdFeedbackDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackDTO> updateFeedback(@PathVariable("id") Long feedbackId, @Valid @RequestBody FeedbackDTO feedbackDTO) {
        FeedbackDTO updatedFeedbackDTO = feedbackService.updateFeedback(feedbackId, feedbackDTO);
        return new ResponseEntity<>(updatedFeedbackDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("id") Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

