package com.example.demo;

import com.example.demo.db.Book;
import com.example.demo.db.BookRepository;
import com.example.demo.service.BookDTO;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;  // Mocking service layer

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
        bookRepository.save(new Book("lRtdEAAAQBAJ", "Spring in Action", "Craig Walls",200));
        bookRepository.save(new Book("12muzgEACAAJ", "Effective Java", "Joshua Bloch",300));
    }

    @Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/books"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Spring in Action"))
            .andExpect(jsonPath("$[1].title").value("Effective Java"));
    }

    @Test
    void testAddBooks() throws Exception {

        BookDTO mockBookDTO1 = new BookDTO("Hello book","manjit",200);

        mockMvc.perform(MockMvcRequestBuilders.post("/books/code124")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockBookDTO1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("code124"))
                .andExpect(jsonPath("$.title").value("Hello book"))
                .andExpect(jsonPath("$.author").value("manjit"))
                .andExpect(jsonPath("$.pageCount").value(200));




    }
}
