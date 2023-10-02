package com.TechnoWebDistributed.tp1.repository;

import com.TechnoWebDistributed.tp1.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Integer> {
    List<StudentEntity> findAll();
    Optional<StudentEntity> findById(Long id);
    Optional<StudentEntity> findByEmail(String email);
    Optional<StudentEntity> findByFirstNameAndLastName(String firstName, String lastName);

    List<StudentEntity> findByFirstName(String firstName);

    List<StudentEntity> findByAgeGreaterThan(int i);
}
