package com.satoru.book.service;

import com.satoru.book.model.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long bookId);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long bookId, BookDTO bookDTO);
    void deleteBook(Long bookId);
}