package com.satoru.book.service;

import com.satoru.book.model.dto.BookTransactionHistoryDTO;
import com.satoru.book.model.dto.responseDto.BookTransactionHistoryRespDTO;

import java.util.List;

public interface BookTransactionHistoryService {
    List<BookTransactionHistoryRespDTO> getAllBookTransactionHistories();
    BookTransactionHistoryRespDTO getBookTransactionHistoryById(Long historyId);
    BookTransactionHistoryDTO createBookTransactionHistory(BookTransactionHistoryDTO bookTransactionHistoryDTO);
    BookTransactionHistoryDTO updateBookTransactionHistory(Long historyId, BookTransactionHistoryDTO bookTransactionHistoryDTO);
    void deleteBookTransactionHistory(Long historyId);
}