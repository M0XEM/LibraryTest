package ru.zyryanov.crud.library.testlibrary.services;

import ru.zyryanov.crud.library.testlibrary.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book addBook(Book book);
    List<Book> searchBooksByTitle(String title);
    void markBookAsIssued(Book book);
    void markBookAsReturned(Book book);
}
