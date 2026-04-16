package com.satoru.book.service;

import com.satoru.book.model.dto.BookTransactionHistoryDTO;

import java.util.List;

public interface BookTransactionHistoryService {
    List<BookTransactionHistoryDTO> getAllBookTransactionHistories();
    BookTransactionHistoryDTO getBookTransactionHistoryById(Long historyId);
    BookTransactionHistoryDTO createBookTransactionHistory(BookTransactionHistoryDTO bookTransactionHistoryDTO);
    BookTransactionHistoryDTO updateBookTransactionHistory(Long historyId, BookTransactionHistoryDTO bookTransactionHistoryDTO);
    void deleteBookTransactionHistory(Long historyId);
}