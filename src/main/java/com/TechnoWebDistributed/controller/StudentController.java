package com.TechnoWebDistributed.controller;


import com.TechnoWebDistributed.model.StudentEntity;
import com.TechnoWebDistributed.service.implementation.BookServiceImpl;
import com.TechnoWebDistributed.service.implementation.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
@CrossOrigin(origins = {"http://localhost:4200"})
public class StudentController {

    private final StudentServiceImpl studentServiceImpl;
    private final BookServiceImpl bookServiceImpl;

    // Récupérer tous les étudiants de la base de données
    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentServiceImpl.getAll();
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    // Récupérer un étudiant par son email
    @GetMapping("/{email}")
    public ResponseEntity<Optional<StudentEntity>> getStudentByEmail(@PathVariable String email) {
        Optional<StudentEntity> student = studentServiceImpl.getByEmail(email);
        if (student.isPresent()) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer tous les étudiants par leur prénom
    @GetMapping("/byFirstName/{firstName}")
    public ResponseEntity<List<StudentEntity>> getStudentsByFirstName(@PathVariable String firstName) {
        List<StudentEntity> students = studentServiceImpl.getByFirstName(firstName);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    @GetMapping("/byLastName/{lastName}")
    public ResponseEntity<List<StudentEntity>> getStudentsByLastName(@PathVariable String lastName) {
        List<StudentEntity> students = studentServiceImpl.getByLastName(lastName);
        if (students.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(students);
        }
    }

    // Récupérer tous les étudiants moins âgés que 30 ans
    @GetMapping("/youngerThan30")
    public ResponseEntity<Optional<StudentEntity>> getStudentsYoungerThan30() {
        List<StudentEntity> youngerThan30 = studentServiceImpl.getYoungerThan30();
        if (youngerThan30.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentServiceImpl.getYoungerThan30().stream().findFirst());
        }
    }

    // Sauvegarder un nouvel étudiant
    @PostMapping(path = "/create", consumes = "application/json")
    public ResponseEntity<Optional<StudentEntity>> saveStudent(@RequestBody StudentEntity requestBody) {
        StudentEntity student = StudentEntity.builder()
                .firstName(requestBody.getFirstName())
                .lastName(requestBody.getLastName())
                .email(requestBody.getEmail())
                .age(requestBody.getAge())
                .build();
        Optional<StudentEntity> savedStudent = studentServiceImpl.createStudent(student);
        if (savedStudent.isPresent()) {
            return ResponseEntity.ok(savedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    // Modifier l'email d'un étudiant par son ancienne adresse email
    @PutMapping("/email/update")
    public ResponseEntity<String> updateStudentEmail(@RequestParam String oldEmail, @RequestParam String newEmail) {
        Optional<StudentEntity> studentEntityResult = studentServiceImpl.updateEmailByOldEmail(oldEmail, newEmail);
        return studentEntityResult.map(studentEntity -> ResponseEntity.ok("The mail has been successfully updated to " + studentEntity.getEmail() + ".")).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Modifier l'âge de tous les étudiants (incrémenter de 1)
    @PutMapping("/incrementAge")
    public ResponseEntity<String> incrementAgeOfAllStudents() {
        if (studentServiceImpl.incrementAge()) {
            return ResponseEntity.ok("Students' age incremented successfully.");
        }
        return null;
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
    public ResponseEntity<String> updateStudentBooks(@PathVariable UUID studentId, @RequestBody Collection<UUID> newBookIds) {
        Optional<StudentEntity> studentOptional = studentServiceImpl.getById(studentId);
        if (studentOptional.isPresent()) {
            StudentEntity studentEntity = studentOptional.get();
            try {
                StudentEntity result = studentServiceImpl.updateStudentBooks(studentEntity, newBookIds);
                if (result == null) {
                    return ResponseEntity.badRequest().body("Error while updating student books.");
                }

//                return ResponseEntity.ok().body(result.toString());
                return ResponseEntity.ok().body(bookServiceImpl.getByIdIn(newBookIds).toString());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(
                        "Error while updating student books."
                        + "\n" + e.getMessage()
                );
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
