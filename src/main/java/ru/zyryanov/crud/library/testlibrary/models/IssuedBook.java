package ru.zyryanov.crud.library.testlibrary.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "issued_books")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssuedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime issueDate;

    private LocalDateTime returnDate;

    @PrePersist
    public void onCreate() {
        this.issueDate = LocalDateTime.now();
    }

}
