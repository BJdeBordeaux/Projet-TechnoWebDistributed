package com.TechnoWebDistributed.tp1.controller;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import com.TechnoWebDistributed.tp1.service.implementation.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<BookEntity> getBookByCode(@PathVariable String code) {
        Optional<BookEntity> book = bookService.getByCode(code);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BookEntity> saveBook(@RequestBody BookEntity bookEntity) {
        BookEntity savedBookEntity = bookService.save(bookEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookEntity);
    }

}
