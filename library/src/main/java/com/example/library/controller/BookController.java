package com.example.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.api.ApiResponse;
import com.example.library.dto.BookRequest;
import com.example.library.dto.BookResponse;
import com.example.library.entity.Book;
import com.example.library.service.BookService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<BookResponse> create(
            @Valid @RequestBody BookRequest request) {
        log.info("Received create book request");
        BookResponse response = bookService.create(request);
        return ApiResponse.success("Book created successfully", response);
    }

    @GetMapping
    public ApiResponse<List<BookResponse>> getAll() {
        log.info("Received get all book request");
        return ApiResponse.success(
                "Books retrieved successfully",
                bookService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<BookResponse> getById(@PathVariable Long id) {
        log.info("Received get id " + id + " book request");
        BookResponse book = bookService.findById(id);
        return ApiResponse.success("Book retrieved successfully", book);
    }

    @PutMapping("/{id}")
    public ApiResponse<BookResponse> update(@PathVariable Long id, @RequestBody Book book) {
        log.info("Received update id " + id + " book request");
        BookResponse updatedBook = bookService.update(id, book);
        return ApiResponse.success("Book updated successfully", updatedBook);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("Received delete id " + id + " book request");
        bookService.delete(id);
    }
}
