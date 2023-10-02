package com.TechnoWebDistributed.tp1.service;

import com.TechnoWebDistributed.tp1.model.Book;
import com.TechnoWebDistributed.tp1.model.Student;
import com.TechnoWebDistributed.tp1.repository.StudentEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentEntityRepository studentEntityRepository;

    public List<Student> getAll() {
        return studentEntityRepository.findAll();
    }

    public Optional<Student> getById(Integer id) {
        return studentEntityRepository.findById(id);
    }

    public Optional<Student> getByEmail(String email) {
        return studentEntityRepository.findByEmail(email);
    }

    public List<Student> getByFirstName(String firstName) {
        return studentEntityRepository.findByFirstName(firstName);
    }

    public List<Student> getOlderThan20() {
        return studentEntityRepository.findByAgeGreaterThan(20);
    }

    public Student save(Student student) {
        return studentEntityRepository.save(student);
    }

    public Student updateEmail(Integer id, String newEmail) {
        Optional<Student> studentOptional = studentEntityRepository.findById(id);
        studentOptional.ifPresent(student -> student.setEmail(newEmail));
        return studentEntityRepository.save(studentOptional.orElse(null));
    }

    public Student updateLastName(String email, String newLastName) {
        Optional<Student> studentOptional = studentEntityRepository.findByEmail(email);
        studentOptional.ifPresent(student -> student.setLastName(newLastName));
        return studentEntityRepository.save(studentOptional.orElse(null));
    }

    public List<Student> incrementAge() {
        List<Student> students = studentEntityRepository.findAll();
        students.forEach(student -> student.setAge(student.getAge() + 1));
        return studentEntityRepository.saveAll(students);
    }

    public Optional<Student> delete(Integer studentId) {
        Optional<Student> studentOptional = studentEntityRepository.findById(studentId);
        studentOptional.ifPresent(studentEntityRepository::delete);
        return studentOptional;
    }

    /////////////////////// BOOKS ///////////////////////
    public void updateStudentBooks(Student student, List<Book> newBooks) {
        // Si l'étudiant n'est pas null
        // Alors, mettez à jour sa liste de livres avec la nouvelle liste de livres
        if (student != null){
            student.setBooks(newBooks);
            // Sauvegarder l'étudiant pour mettre à jour sa liste de livres dans la base de données
            // Cela sera écrit dans StudentService.java
        }
        // Si l'étudiant est null, throw une exception IllegalArgumentException
        else{
            throw new IllegalArgumentException("Student is null");
        }
    }

}
