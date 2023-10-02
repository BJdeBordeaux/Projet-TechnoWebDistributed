package com.TechnoWebDistributed.tp1.service.implementation;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import com.TechnoWebDistributed.tp1.model.StudentEntity;
import com.TechnoWebDistributed.tp1.repository.BookEntityRepository;
import com.TechnoWebDistributed.tp1.repository.StudentEntityRepository;
import com.TechnoWebDistributed.tp1.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentEntityRepository studentEntityRepository;

    public List<StudentEntity> getAll() {
        return studentEntityRepository.findAll();
    }

    public Optional<StudentEntity> getById(Long id) {
        return studentEntityRepository.findById(id);
    }

    public Optional<StudentEntity> getByEmail(String email) {
        return studentEntityRepository.findByEmail(email);
    }

    public List<StudentEntity> getByFirstName(String firstName) {
        return studentEntityRepository.findByFirstName(firstName);
    }

    public List<StudentEntity> getOlderThan20() {
        return studentEntityRepository.findByAgeGreaterThan(20);
    }
//
//    public StudentEntity createStudent(StudentEntity studentEntity) {
//        StudentEntity studentEntity = new StudentEntity();
//        return studentEntityRepository.save(studentEntity);
//    }

    public StudentEntity save(StudentEntity studentEntity) {
        return studentEntityRepository.save(studentEntity);
    }

    public StudentEntity updateEmail(Integer id, String newEmail) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findById(id);
        studentOptional.ifPresent(student -> student.setEmail(newEmail));
        return studentEntityRepository.save(studentOptional.orElse(null));
    }

    public StudentEntity updateLastName(String email, String newLastName) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findByEmail(email);
        studentOptional.ifPresent(student -> student.setLastName(newLastName));
        return studentEntityRepository.save(studentOptional.orElse(null));
    }

    public List<StudentEntity> incrementAge() {
        List<StudentEntity> studentEntities = studentEntityRepository.findAll();
        studentEntities.forEach(student -> student.setAge(student.getAge() + 1));
        return studentEntityRepository.saveAll(studentEntities);
    }

    public Optional<StudentEntity> delete(Long studentId) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findById(studentId);
        studentOptional.ifPresent(studentEntityRepository::delete);
        return studentOptional;
    }

    /////////////////////// BOOKS ///////////////////////
    public void updateStudentBooks(StudentEntity studentEntity, List<Long> newBookIds) {
        // Si l'étudiant n'est pas null
        // Alors, mettez à jour sa liste de livres avec la nouvelle liste de livres
        if (studentEntity != null){
            // Trouver les livres avec ces IDs
//            BookEntityRepository.findByI

//            studentEntity.setBookEntities(newBookIds);
            // Sauvegarder l'étudiant pour mettre à jour sa liste de livres dans la base de données
            // Cela sera écrit dans StudentService.java
        }
        // Si l'étudiant est null, throw une exception IllegalArgumentException
        else{
            throw new IllegalArgumentException("Student is null");
        }
    }

}
