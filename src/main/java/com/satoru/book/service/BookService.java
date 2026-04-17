package com.satoru.book.service;

import com.satoru.book.model.dto.BookDTO;
import com.satoru.book.model.dto.responseDto.BookRespDTO;

import java.util.List;

public interface BookService {
    List<BookRespDTO> getAllBooks();
    BookRespDTO getBookById(Long bookId);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long bookId, BookDTO bookDTO);
    void deleteBook(Long bookId);
}