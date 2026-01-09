package com.example.library.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.library.dto.BookRequest;
import com.example.library.dto.BookResponse;
import com.example.library.entity.Book;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void createBook__WithValidData__ShouldReturnSuccess() {
        BookRequest request = new BookRequest("Confessions", "Minato Kanae");

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle("Confessions");
        savedBook.setAuthor("Minato Kanae");

        when(bookRepository.save(any())).thenReturn(savedBook);

        BookResponse response = bookService.create(request);

        assertNotEquals(response, null);
        assertEquals(response.getId(), savedBook.getId());
        assertEquals(savedBook.getTitle(), response.getTitle());
        assertEquals(savedBook.getAuthor(), response.getAuthor());
    }

    @Test
    void findById__NotFound__ShouldThrowResourceNotFoundException() {
        Long bookId = 999L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> bookService.findById(bookId));
        // Optional: verify message
        assertEquals("Book not found with id " + bookId, exception.getMessage());
    }
}
