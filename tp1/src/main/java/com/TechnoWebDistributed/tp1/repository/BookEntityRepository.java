package com.TechnoWebDistributed.tp1.repository;

import com.TechnoWebDistributed.tp1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface BookEntityRepository extends JpaRepository<Book, Long> {

    List<Book> findAll();

    Optional<Book> findByCode(String code);


}
