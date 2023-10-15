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

    @GetMapping("/byLastName/{lastName}")
    public List<StudentEntity> getStudentsByLastName(@PathVariable String lastName) {
        return studentServiceImpl.getByLastName(lastName);
    }

    // Récupérer tous les étudiants moins âgés que 30 ans
    @GetMapping("/youngerThan30")
    public List<StudentEntity> getStudentsYoungerThan30() {
        return studentServiceImpl.getYoungerThan30();
    }

    // Sauvegarder un nouvel étudiant
    // TODO: finish it. Look how to build a StudentEntity object by @RequestBody
    @PostMapping(path = "/create", consumes = "application/json")
    public StudentEntity saveStudent(@RequestBody StudentEntity studentEntity) {
        StudentEntity.builder().firstName();
        return studentServiceImpl.createStudent(studentEntity);
    }

    // Modifier l'email d'un étudiant par son ancienne adresse email
    @PutMapping("/email/update")
    public StudentEntity updateStudentEmail(@RequestParam String oldEmail, @RequestParam String newEmail) {
        return studentServiceImpl.updateEmailByOldEmail(oldEmail, newEmail);
    }

    // Modifier l'âge de tous les étudiants (incrémenter de 1)
    @PutMapping("/incrementAge")
    public List<StudentEntity> incrementAgeOfAllStudents() {
        return studentServiceImpl.incrementAge();
    }

    // Supprimer un étudiant par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<StudentEntity>> deleteStudent(@PathVariable UUID id) {
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
