package com.TechnoWebDistributed.tp1.repository;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookEntityRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAll();

//    List<BookEntity> findById(List<UUID> ids);

    Optional<BookEntity> findByCode(String code);


}
