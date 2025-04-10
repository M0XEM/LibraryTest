package ru.zyryanov.crud.library.testlibrary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zyryanov.crud.library.testlibrary.models.Book;
import ru.zyryanov.crud.library.testlibrary.models.IssuedBook;
import ru.zyryanov.crud.library.testlibrary.models.User;

import java.util.List;
import java.util.Optional;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Long> {
    List<IssuedBook> findByUser(User user);
    Optional<IssuedBook> findByBookAndReturnDateIsNull(Book book);
}