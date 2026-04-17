package com.satoru.book.service.impl;

import com.satoru.book.model.dto.FeedbackDTO;
import com.satoru.book.model.dto.responseDto.FeedbackRespDTO;
import com.satoru.book.model.entity.Feedback;
import com.satoru.book.repository.FeedbackRepository;
import com.satoru.book.service.FeedbackService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository, ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<FeedbackRespDTO> getAllFeedbacks() {
        try {
            List<Feedback> feedbacks = feedbackRepository.findAll();
            return feedbacks.stream()
                    .map(feedback -> modelMapper.map(feedback, FeedbackRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all feedbacks: " + e.getMessage());
        }
    }

    @Override
    public FeedbackRespDTO getFeedbackById(Long feedbackId) {
        try {
            Feedback feedback = feedbackRepository.findById(feedbackId)
                    .orElseThrow(() -> new NotFoundException("Feedback not found with ID: " + feedbackId));
            return modelMapper.map(feedback, FeedbackRespDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch feedback with ID " + feedbackId + ": " + e.getMessage());
        }
    }

    @Override
    public FeedbackDTO createFeedback(FeedbackDTO feedbackDTO) {
        try {
            Feedback feedback = modelMapper.map(feedbackDTO, Feedback.class);
            feedback = feedbackRepository.save(feedback);
            return modelMapper.map(feedback, FeedbackDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create feedback: " + e.getMessage());
        }
    }

    @Override
    public FeedbackDTO updateFeedback(Long feedbackId, FeedbackDTO feedbackDTO) {
        try {
            Feedback existingFeedback = feedbackRepository.findById(feedbackId)
                    .orElseThrow(() -> new NotFoundException("Feedback not found with ID: " + feedbackId));
            modelMapper.map(feedbackDTO, existingFeedback);
            existingFeedback.setFeedbackId(feedbackId);
            existingFeedback = feedbackRepository.save(existingFeedback);
            return modelMapper.map(existingFeedback, FeedbackDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update feedback with ID " + feedbackId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        try {
            if (!feedbackRepository.existsById(feedbackId)) {
                throw new NotFoundException("Feedback not found with ID: " + feedbackId);
            }
            feedbackRepository.deleteById(feedbackId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete feedback with ID " + feedbackId);
        }
    }
}