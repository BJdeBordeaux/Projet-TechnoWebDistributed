package com.TechnoWebDistributed.tp1.controller;


import com.TechnoWebDistributed.tp1.model.Book;
import com.TechnoWebDistributed.tp1.model.Student;
import com.TechnoWebDistributed.tp1.repository.StudentEntityRepository;
import com.TechnoWebDistributed.tp1.service.BookService;
import com.TechnoWebDistributed.tp1.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentEntityRepository studentEntityRepository;
    private final StudentService studentService;
    private final BookService bookService;

    // Récupérer tous les étudiants de la base de données
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    // Récupérer un étudiant par son email
    @GetMapping("/{email}")
    public Optional<Student> getStudentByEmail(@PathVariable String email) {
        return studentService.getByEmail(email);
    }

    // Récupérer tous les étudiants par leur prénom
    @GetMapping("/byFirstName/{firstName}")
    public List<Student> getStudentsByFirstName(@PathVariable String firstName) {
        return studentService.getByFirstName(firstName);
    }

    // Récupérer tous les étudiants plus âgés que 20 ans
    @GetMapping("/olderThan20")
    public List<Student> getStudentsOlderThan20() {
        return studentService.getOlderThan20();
    }

    // Sauvegarder un nouvel étudiant
    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentEntityRepository.save(student);
    }

    // Modifier l'email d'un étudiant par son ID
    @PutMapping("/{id}/email")
    public Student updateStudentEmail(@PathVariable Integer id, @RequestParam String newEmail) {
        return studentService.updateEmail(id, newEmail);
    }

    // Modifier le nom de famille d'un étudiant par son email
    @PutMapping("/{email}/lastname")
    public Student updateStudentLastName(@PathVariable String email, @RequestParam String newLastName) {
        return studentService.updateLastName(email, newLastName);
    }

    // Modifier l'âge de tous les étudiants (incrémenter de 1)
    @PutMapping("/incrementAge")
    public List<Student> incrementAgeOfAllStudents() {
        return studentService.incrementAge();
    }

    // Supprimer un étudiant par son ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<Student>> deleteStudent(@PathVariable Integer id) {
        Optional<Student> deletedStudent = studentService.delete(id);

        if (studentService.getById(id).isEmpty()) {
            return ResponseEntity.ok(deletedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //////////////////// BOOKS ////////////////////

    @PutMapping("/{studentId}/updateBooks")
    public ResponseEntity<String> updateStudentBooks(@PathVariable Integer studentId, @RequestBody List<Book> newBooks) {
        Optional<Student> studentOptional = studentEntityRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            studentService.updateStudentBooks(student, newBooks);
            // sauvagarde de l'étudiant pour mettre à jour sa liste de livres dans la base de données
            studentEntityRepository.save(student);
            return ResponseEntity.ok("Student books updated successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
