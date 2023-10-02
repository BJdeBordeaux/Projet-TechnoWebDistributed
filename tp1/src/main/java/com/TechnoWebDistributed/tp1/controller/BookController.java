package com.TechnoWebDistributed.tp1.controller;

import com.TechnoWebDistributed.tp1.model.Book;
import com.TechnoWebDistributed.tp1.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Book> getBookByCode(@PathVariable String code) {
        Optional<Book> book = bookService.getByCode(code);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/updateStudentBooks")
    public ResponseEntity<String> updateStudentBooks(@RequestParam Long studentId, @RequestBody List<Book> newBooks) {
        // Obtenez l'étudiant à partir de votre service StudentService (selon l'ID)

        // Puis appelez bookService.updateStudentBooks(student, newBooks)
        // Assurez-vous que l'étudiant existe en base de données avant d'appeler cette méthode.
        // ...
        return ResponseEntity.ok("Student books updated successfully.");
    }
}
