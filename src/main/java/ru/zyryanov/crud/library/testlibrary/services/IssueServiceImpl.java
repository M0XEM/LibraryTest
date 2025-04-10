package ru.zyryanov.crud.library.testlibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zyryanov.crud.library.testlibrary.models.Book;
import ru.zyryanov.crud.library.testlibrary.models.IssuedBook;
import ru.zyryanov.crud.library.testlibrary.models.User;
import ru.zyryanov.crud.library.testlibrary.repositories.BookRepository;
import ru.zyryanov.crud.library.testlibrary.repositories.IssuedBookRepository;
import ru.zyryanov.crud.library.testlibrary.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final IssuedBookRepository issuedBookRepository;

    @Async("taskExecutor")
    @Transactional
    @Override
    public CompletableFuture<Void> issueBookToUser(Long bookId, String username) {
        synchronized (bookId.toString().intern()) {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new RuntimeException("Книга не найдена"));

            if (!book.getAvailable() ||
                    issuedBookRepository.findByBookAndReturnDateIsNull(book).isPresent()) {
                throw new RuntimeException("Книга уже выдана");
            }

            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

            IssuedBook issuedBook = new IssuedBook();
            issuedBook.setBook(book);
            issuedBook.setUser(user);

            book.setAvailable(false);
            bookRepository.save(book);
            issuedBookRepository.save(issuedBook);

            return CompletableFuture.completedFuture(null);
        }
    }


    @Transactional
    @Override
    public void returnBookFromUser(Long bookId, String username) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Книга не найдена"));

        IssuedBook issuedBook = issuedBookRepository.findByBookAndReturnDateIsNull(book)
                .orElseThrow(() -> new RuntimeException("Книга не была выдана"));

        if (!issuedBook.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Книга выдана другому пользователю");
        }

        issuedBook.setReturnDate(LocalDateTime.now());
        issuedBookRepository.save(issuedBook);

        book.setAvailable(true);
        bookRepository.save(book);
    }

    @Override
    public List<IssuedBook> getIssuedBooksForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return issuedBookRepository.findByUser(user);
    }

}
