package com.TechnoWebDistributed.tp1.service.implementation;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import com.TechnoWebDistributed.tp1.repository.BookEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookEntityRepository bookRepository;

    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    public Optional<BookEntity> getByCode(String code) {
        return bookRepository.findByCode(code);
    }

    public BookEntity save(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

}
