package ru.zyryanov.crud.library.testlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyryanov.crud.library.testlibrary.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
}
