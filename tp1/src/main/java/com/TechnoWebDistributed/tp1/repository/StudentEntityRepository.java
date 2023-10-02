package com.TechnoWebDistributed.tp1.repository;

import com.TechnoWebDistributed.tp1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface StudentEntityRepository extends JpaRepository<Student, Integer> {
    List<Student> findAll();
    Optional<Student> findById(Integer id);
    Optional<Student> findByEmail(String email);
    Optional<Student> findByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findByFirstName(String firstName);

    List<Student> findByAgeGreaterThan(int i);
}
