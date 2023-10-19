package com.TechnoWebDistributed.tp1.controller;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import com.TechnoWebDistributed.tp1.service.implementation.BookServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookServiceImpl.getAll();
    }

    // get book by id
    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable UUID id) {
        Optional<BookEntity> book = bookServiceImpl.getById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<BookEntity> getBookByCode(@PathVariable String code) {
        Optional<BookEntity> book = bookServiceImpl.getByCode(code);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get book by name
    @GetMapping("/name/{name}")
    public ResponseEntity<BookEntity> getBookByName(@PathVariable String name) {
        Optional<BookEntity> book = bookServiceImpl.getByName(name);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<BookEntity> saveBook(@RequestBody BookEntity bookEntity) {
        BookEntity book = BookEntity.builder()
                .code(bookEntity.getCode())
                .name(bookEntity.getName())
                .build();
        BookEntity savedBookEntity = bookServiceImpl.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBookEntity);
    }

}
