package com.TechnoWebDistributed.tp1.controller;


import com.TechnoWebDistributed.tp1.model.BookEntity;
import com.TechnoWebDistributed.tp1.model.StudentEntity;
import com.TechnoWebDistributed.tp1.repository.StudentEntityRepository;
import com.TechnoWebDistributed.tp1.service.implementation.BookService;
import com.TechnoWebDistributed.tp1.service.implementation.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentEntityRepository studentEntityRepository;
    private final StudentServiceImpl studentServiceImpl;
    private final BookService bookService;

    // Récupérer tous les étudiants de la base de données
    @GetMapping
    public List<StudentEntity> getAllStudents() {
        return studentServiceImpl.getAll();
    }

    // Récupérer un étudiant par son email
    @GetMapping("/{email}")
    public Optional<StudentEntity> getStudentByEmail(@PathVariable String email) {
        return studentServiceImpl.getByEmail(email);
    }

    // Récupérer tous les étudiants par leur prénom
    @GetMapping("/byFirstName/{firstName}")
    public List<StudentEntity> getStudentsByFirstName(@PathVariable String firstName) {
        return studentServiceImpl.getByFirstName(firstName);
    }

    // Récupérer tous les étudiants plus âgés que 20 ans
    @GetMapping("/olderThan20")
    public List<StudentEntity> getStudentsOlderThan20() {
        return studentServiceImpl.getOlderThan20();
    }

    // Sauvegarder un nouvel étudiant
    @PostMapping(path = "/create", consumes = "application/json")
    public StudentEntity saveStudent(@RequestBody StudentEntity studentEntity) {
        return studentEntityRepository.save(studentEntity);
    }

    // Modifier l'email d'un étudiant par son ID
    @PutMapping("/{id}/email")
    public StudentEntity updateStudentEmail(@PathVariable Integer id, @RequestParam String newEmail) {
        return studentServiceImpl.updateEmail(id, newEmail);
    }

    // Modifier le nom de famille d'un étudiant par son email
    @PutMapping("/{email}/lastname")
    public StudentEntity updateStudentLastName(@PathVariable String email, @RequestParam String newLastName) {
        return studentServiceImpl.updateLastName(email, newLastName);
    }

    // Modifier l'âge de tous les étudiants (incrémenter de 1)
    @PutMapping("/incrementAge")
    public List<StudentEntity> incrementAgeOfAllStudents() {
        return studentServiceImpl.incrementAge();
    }

    // Supprimer un étudiant par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<StudentEntity>> deleteStudent(@PathVariable Long id) {
        Optional<StudentEntity> deletedStudent = studentServiceImpl.delete(id);

        if (studentServiceImpl.getById(id).isEmpty()) {
            return ResponseEntity.ok(deletedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //////////////////// BOOKS ////////////////////

    @PutMapping("/{studentId}/updateBooks")
    public ResponseEntity<String> updateStudentBooks(@PathVariable Integer studentId, @RequestBody List<Long> newBookIds) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            StudentEntity studentEntity = studentOptional.get();
            // TODO: finish
            studentServiceImpl.updateStudentBooks(studentEntity, newBookIds);
            // sauvagarde de l'étudiant pour mettre à jour sa liste de livres dans la base de données
            studentEntityRepository.save(studentEntity);
            return ResponseEntity.ok("Student books updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
