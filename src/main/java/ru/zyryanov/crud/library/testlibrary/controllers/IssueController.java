package ru.zyryanov.crud.library.testlibrary.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.zyryanov.crud.library.testlibrary.models.IssuedBook;
import ru.zyryanov.crud.library.testlibrary.services.IssueService;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @Operation(summary = "Выдать книгу пользователю")
    @PostMapping("/{id}/issue")
    public ResponseEntity<String> issueBook(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();

        try {
            issueService.issueBookToUser(id, username).get();
            return ResponseEntity.ok("Книга выдана пользователю " + username);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            return ResponseEntity.badRequest().body("Ошибка: " + cause.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Системная ошибка: " + e.getMessage());
        }
    }


    @Operation(summary = "Вернуть книгу")
    @PostMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        issueService.returnBookFromUser(id, username);
        return ResponseEntity.ok("Книга возвращена пользователем " + username);
    }

    @Operation(summary = "Получить список книг, выданных текущему пользователю")
    @GetMapping("/issued")
    public List<IssuedBook> getMyIssuedBooks(Authentication authentication) {
        String username = authentication.getName();
        return issueService.getIssuedBooksForUser(username);
    }
}

