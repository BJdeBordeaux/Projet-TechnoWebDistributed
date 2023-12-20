package com.TechnoWebDistributed.service;

import com.TechnoWebDistributed.model.BookEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookService {
    public List<BookEntity> getAll();

    public Optional<BookEntity> getById(UUID id);

    public Optional<BookEntity> createBook(BookEntity bookEntity);

    public Optional<BookEntity> delete(UUID studentId);

}
