package com.example.library.controller;

import com.example.library.dto.BookRequest;
import com.example.library.dto.BookResponse;
import com.example.library.service.BookService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper; // for converting objects to JSON

    @BeforeEach
    void setUp() {
        // No setup needed; MockMvc is autowired
    }

    // ---------------- SUCCESS CASE ----------------
    @Test
    void createBook_success() throws Exception {
        BookRequest request = new BookRequest("Clean Code", "Robert C. Martin");
        BookResponse response = new BookResponse(1L, "Clean Code", "Robert C. Martin");

        when(bookService.create(any(BookRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("SUCCESS"))
                .andExpect(jsonPath("$.data.title").value("Clean Code"))
                .andExpect(jsonPath("$.data.author").value("Robert C. Martin"));
    }

    // ---------------- VALIDATION ERROR CASE ----------------
    @Test
    void createBook__NullTitleAndAuthor__ShouldReturnBadRequest() throws Exception {
        BookRequest invalidRequest = new BookRequest(null, null);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    // ---------------- GET ALL BOOKS EXAMPLE ----------------
    @Test
    void getAllBooks__ShouldReturnBookList() throws Exception {
        BookResponse book1 = new BookResponse(1L, "Clean Code", "Robert C. Martin");
        BookResponse book2 = new BookResponse(2L, "Effective Java", "Joshua Bloch");

        when(bookService.findAll()).thenReturn(java.util.List.of(book1, book2));

        mockMvc.perform(get("/api/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].title").value("Clean Code"))
                .andExpect(jsonPath("$.data[1].title").value("Effective Java"));
    }
}
