package com.TechnoWebDistributed.repository;

import com.TechnoWebDistributed.model.BookEntity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Table(name = "book")
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAll();

    List<BookEntity> findByIdIn(Collection<UUID> uuids);

    Optional<BookEntity> findById(UUID id);

    Optional<BookEntity> findByCode(String code);

    Optional<BookEntity> findByName(String name);


}
