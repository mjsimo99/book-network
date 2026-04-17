package com.satoru.book.repository;

import com.satoru.book.model.entity.Book;
import com.satoru.book.model.entity.BookTransactionHistory;
import com.satoru.book.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Long> {
    List<BookTransactionHistory> findByUser(User user);
    List<BookTransactionHistory> findByBook(Book book);
    // i want all book by users

}
