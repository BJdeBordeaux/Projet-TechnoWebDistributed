package com.TechnoWebDistributed.tp1.service;

import com.TechnoWebDistributed.tp1.model.Book;
import com.TechnoWebDistributed.tp1.model.Student;
import com.TechnoWebDistributed.tp1.repository.BookEntityRepository;
import com.TechnoWebDistributed.tp1.repository.StudentEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookEntityRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getByCode(String code) {
        return bookRepository.findByCode(code);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

}
