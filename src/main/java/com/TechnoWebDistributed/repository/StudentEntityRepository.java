package com.TechnoWebDistributed.repository;

import com.TechnoWebDistributed.model.StudentEntity;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Table(name = "student")
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Integer> {
    List<StudentEntity> findAll();
    Optional<StudentEntity> findById(UUID id);
    Optional<StudentEntity> findByEmail(String email);

    List<StudentEntity> findByFirstName(String firstName);

    List<StudentEntity> findByLastName(String lastName);

    Optional<StudentEntity> findByFirstNameAndLastName(String firstName, String lastName);

    List<StudentEntity> findByAgeGreaterThan(int i);

    List<StudentEntity> findByAgeLessThan(int i);

}
