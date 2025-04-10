package ru.zyryanov.crud.library.testlibrary.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zyryanov.crud.library.testlibrary.models.Book;
import ru.zyryanov.crud.library.testlibrary.repositories.BookRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findAll().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public void markBookAsIssued(Book book) {
        book.setAvailable(false);
        bookRepository.save(book);
    }

    @Override
    public void markBookAsReturned(Book book) {
        book.setAvailable(true);
        bookRepository.save(book);
    }
}
