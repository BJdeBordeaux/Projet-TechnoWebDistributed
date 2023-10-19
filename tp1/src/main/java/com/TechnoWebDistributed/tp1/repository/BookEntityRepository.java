package com.TechnoWebDistributed.tp1.repository;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import com.TechnoWebDistributed.tp1.model.StudentEntity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Table(name = "book")
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAll();

    Optional<BookEntity> findById(UUID id);

    Optional<BookEntity> findByCode(String code);

    Optional<BookEntity> findByName(String name);


}
