package com.satoru.book.service.impl;

import com.satoru.book.model.dto.BookDTO;
import com.satoru.book.model.entity.Book;
import com.satoru.book.repository.BookRepository;
import com.satoru.book.service.BookService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            return books.stream()
                    .map(book -> modelMapper.map(book, BookDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all books: " + e.getMessage());
        }
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        try {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
            return modelMapper.map(book, BookDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch book with ID " + bookId + ": " + e.getMessage());
        }
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        try {
            Book book = modelMapper.map(bookDTO, Book.class);
            book = bookRepository.save(book);
            return modelMapper.map(book, BookDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create book: " + e.getMessage());
        }
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) {
        try {
            Book existingBook = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
            modelMapper.map(bookDTO, existingBook);
            existingBook.setBookId(bookId);
            existingBook = bookRepository.save(existingBook);
            return modelMapper.map(existingBook, BookDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update book with ID " + bookId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(Long bookId) {
        try {
            if (!bookRepository.existsById(bookId)) {
                throw new RuntimeException("Book not found with ID: " + bookId);
            }
            bookRepository.deleteById(bookId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete book with ID " + bookId);
        }
    }
}