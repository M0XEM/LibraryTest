package ru.zyryanov.crud.library.testlibrary.services;

import ru.zyryanov.crud.library.testlibrary.models.IssuedBook;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IssueService {
    CompletableFuture<Void> issueBookToUser(Long bookId, String username);
    void returnBookFromUser(Long bookId, String username);
    List<IssuedBook> getIssuedBooksForUser(String username);
}
