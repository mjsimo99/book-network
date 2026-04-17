package com.satoru.book.service.impl;

import com.satoru.book.model.dto.BookTransactionHistoryDTO;
import com.satoru.book.model.dto.responseDto.BookTransactionHistoryRespDTO;
import com.satoru.book.model.entity.BookTransactionHistory;
import com.satoru.book.repository.BookTransactionHistoryRepository;
import com.satoru.book.service.BookTransactionHistoryService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookTransactionHistoryServiceImpl implements BookTransactionHistoryService {

    private final BookTransactionHistoryRepository bookTransactionHistoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookTransactionHistoryServiceImpl(BookTransactionHistoryRepository bookTransactionHistoryRepository, ModelMapper modelMapper) {
        this.bookTransactionHistoryRepository = bookTransactionHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookTransactionHistoryRespDTO> getAllBookTransactionHistories() {
        try {
            List<BookTransactionHistory> histories = bookTransactionHistoryRepository.findAll();
            return histories.stream()
                    .map(history -> modelMapper.map(history, BookTransactionHistoryRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all book transaction histories: " + e.getMessage());
        }
    }

    @Override
    public BookTransactionHistoryRespDTO getBookTransactionHistoryById(Long historyId) {
        try {
            BookTransactionHistory transaction = bookTransactionHistoryRepository.findById(historyId)
                    .orElseThrow(() -> new NotFoundException("Book transaction history not found with ID: " + historyId));
            return modelMapper.map(transaction, BookTransactionHistoryRespDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch book transaction history with ID " + historyId + ": " + e.getMessage());
        }
    }

    @Override
    public BookTransactionHistoryDTO createBookTransactionHistory(BookTransactionHistoryDTO transactionDTO) {
        try {
            BookTransactionHistory transaction = modelMapper.map(transactionDTO, BookTransactionHistory.class);
            transaction = bookTransactionHistoryRepository.save(transaction);
            return modelMapper.map(transaction, BookTransactionHistoryDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create book transaction history: " + e.getMessage());
        }
    }

    @Override
    public BookTransactionHistoryDTO updateBookTransactionHistory(Long historyId, BookTransactionHistoryDTO transactionDTO) {
        try {
            BookTransactionHistory existingTransaction = bookTransactionHistoryRepository.findById(historyId)
                    .orElseThrow(() -> new NotFoundException("Book transaction history not found with ID: " + historyId));
            modelMapper.map(transactionDTO, existingTransaction);
            existingTransaction.setHistoryId(historyId);
            existingTransaction = bookTransactionHistoryRepository.save(existingTransaction);
            return modelMapper.map(existingTransaction, BookTransactionHistoryDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update book transaction history with ID " + historyId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteBookTransactionHistory(Long historyId) {
        try {
            if (!bookTransactionHistoryRepository.existsById(historyId)) {
                throw new NotFoundException("Book transaction history not found with ID: " + historyId);
            }
            bookTransactionHistoryRepository.deleteById(historyId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete book transaction history with ID " + historyId);
        }
    }
}