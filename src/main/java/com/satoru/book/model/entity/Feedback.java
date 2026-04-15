package com.satoru.book.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Feedbacks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @Column(name = "note")
    private Integer note;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
