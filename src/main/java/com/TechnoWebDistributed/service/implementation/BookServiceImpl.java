package com.TechnoWebDistributed.service.implementation;

import com.TechnoWebDistributed.model.BookEntity;
import com.TechnoWebDistributed.repository.BookEntityRepository;
import com.TechnoWebDistributed.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookEntityRepository bookEntityRepository;

    public List<BookEntity> getAll() {
        return bookEntityRepository.findAll();
    }

    public Optional<BookEntity> getById(UUID id) {
        return bookEntityRepository.findById(id);
    }

    public List<BookEntity> getByIdIn(Collection<UUID> ids) {
        return bookEntityRepository.findByIdIn(ids);
    }

    public Optional<BookEntity> createBook(BookEntity bookEntity) {
        return Optional.of(bookEntityRepository.save(bookEntity));
    }

    public Optional<BookEntity> createStudent(BookEntity bookEntity) {
        return Optional.empty();
    }

    public Optional<BookEntity> delete(UUID studentId) {
        return Optional.empty();
    }

    public Optional<BookEntity> getByCode(String code) {
        return bookEntityRepository.findByCode(code);
    }

    public Optional<BookEntity> getByName(String name) {
        return bookEntityRepository.findByName(name);
    }

    public BookEntity save(BookEntity bookEntity) {
        return bookEntityRepository.save(bookEntity);
    }

}
