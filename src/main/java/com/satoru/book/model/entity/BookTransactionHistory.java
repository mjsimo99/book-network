package com.satoru.book.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BookTransactionHistories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookTransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @Column(name = "returned")
    private Boolean returned;

    @Column(name = "returnApproved")
    private Boolean returnApproved;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
