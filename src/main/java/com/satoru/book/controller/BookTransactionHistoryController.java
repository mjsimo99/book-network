package com.satoru.book.controller;

import com.satoru.book.model.dto.BookTransactionHistoryDTO;
import com.satoru.book.model.dto.responseDto.BookTransactionHistoryRespDTO;
import com.satoru.book.service.BookTransactionHistoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-transactions")
@CrossOrigin
public class BookTransactionHistoryController {

    private final BookTransactionHistoryService bookTransactionHistoryService;

    public BookTransactionHistoryController(BookTransactionHistoryService bookTransactionHistoryService) {
        this.bookTransactionHistoryService = bookTransactionHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<BookTransactionHistoryRespDTO>> getAllBookTransactionHistories() {
        List<BookTransactionHistoryRespDTO> histories = bookTransactionHistoryService.getAllBookTransactionHistories();
        return new ResponseEntity<>(histories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookTransactionHistoryRespDTO> getBookTransactionHistoryById(@PathVariable("id") Long historyId) {
        BookTransactionHistoryRespDTO historyDTO = bookTransactionHistoryService.getBookTransactionHistoryById(historyId);
        return new ResponseEntity<>(historyDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookTransactionHistoryDTO> createBookTransactionHistory(@Valid @RequestBody BookTransactionHistoryDTO bookTransactionHistoryDTO) {
        BookTransactionHistoryDTO createdHistoryDTO = bookTransactionHistoryService.createBookTransactionHistory(bookTransactionHistoryDTO);
        return new ResponseEntity<>(createdHistoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookTransactionHistoryDTO> updateBookTransactionHistory(@PathVariable("id") Long historyId, @Valid @RequestBody BookTransactionHistoryDTO bookTransactionHistoryDTO) {
        BookTransactionHistoryDTO updatedHistoryDTO = bookTransactionHistoryService.updateBookTransactionHistory(historyId, bookTransactionHistoryDTO);
        return new ResponseEntity<>(updatedHistoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookTransactionHistory(@PathVariable("id") Long historyId) {
        bookTransactionHistoryService.deleteBookTransactionHistory(historyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

