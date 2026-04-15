package com.satoru.book.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long bookId;

    @Column(name = "title")
    private String title;

    @Column(name = "authorName")
    private String authorName;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "bookCover")
    private String bookCover;

    @Column(name = "archived")
    private Boolean archived;

    @Column(name = "shareable")
    private Boolean shareable;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<BookTransactionHistory> transactionHistories = new ArrayList<>();

}
