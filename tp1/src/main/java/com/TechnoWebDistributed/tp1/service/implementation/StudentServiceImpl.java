package com.TechnoWebDistributed.tp1.service.implementation;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import com.TechnoWebDistributed.tp1.model.StudentEntity;
import com.TechnoWebDistributed.tp1.repository.BookEntityRepository;
import com.TechnoWebDistributed.tp1.repository.StudentEntityRepository;
import com.TechnoWebDistributed.tp1.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentEntityRepository studentEntityRepository;
    private final BookEntityRepository bookEntityRepository;

    public List<StudentEntity> getAll() {
        return studentEntityRepository.findAll();
    }

    public Optional<StudentEntity> getById(UUID id) {
        return studentEntityRepository.findById(id);
    }

    public Optional<StudentEntity> getByEmail(String email) {
        return studentEntityRepository.findByEmail(email);
    }

    public List<StudentEntity> getByFirstName(String firstName) {
        return studentEntityRepository.findByFirstName(firstName);
    }

    public List<StudentEntity> getByLastName(String lastName) {
        return studentEntityRepository.findByLastName(lastName);
    }

    public List<StudentEntity> getYoungerThan30() {
        return studentEntityRepository.findByAgeLessThan(30);
    }

    public Optional<StudentEntity> createStudent(StudentEntity studentEntity) {
        return Optional.of(studentEntityRepository.save(studentEntity));
    }

    public StudentEntity updateEmailById(UUID id, String newEmail) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findById(id);
        studentOptional.ifPresent(student -> student.setEmail(newEmail));
        return studentEntityRepository.save(studentOptional.orElse(null));
    }

    public Optional<StudentEntity> updateEmailByOldEmail(String oldEmail, String newEmail) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findByEmail(oldEmail);
        studentOptional.ifPresent(student -> {
            student.setEmail(newEmail);
            studentEntityRepository.save(student);
        });
        return studentOptional;
    }

    public StudentEntity updateLastName(String email, String newLastName) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findByEmail(email);
        studentOptional.ifPresent(student -> student.setLastName(newLastName));
        return studentEntityRepository.save(studentOptional.orElse(null));
    }

    public Boolean incrementAge() {
        try {
            List<StudentEntity> studentEntities = studentEntityRepository.findAll();
            studentEntities.forEach(student -> student.setAge(student.getAge() + 1));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<StudentEntity> delete(UUID studentId) {
        Optional<StudentEntity> studentOptional = studentEntityRepository.findById(studentId);
        studentOptional.ifPresent(studentEntityRepository::delete);
        return studentOptional;
    }

    /////////////////////// BOOKS ///////////////////////
    public Boolean updateStudentBooks(StudentEntity studentEntity, Collection<UUID> newBookIds) {
        // Si l'étudiant n'est pas null
        // Alors, mettez à jour sa liste de livres avec la nouvelle liste de livres
        if (studentEntity != null && newBookIds != null && !newBookIds.isEmpty()){
            // Trouver les livres avec ces IDs
            List<BookEntity> allBooks = bookEntityRepository.findAll(newBookIds);
            // Si aucun livre n'est trouvé, throw une exception IllegalArgumentException
            if (allBooks == null || allBooks.isEmpty()){
                throw new IllegalArgumentException("No book found with these IDs");
            }

            // Sauvegarder l'étudiant pour mettre à jour sa liste de livres dans la base de données
            studentEntity.setBookEntities(allBooks);
            studentEntityRepository.save(studentEntity);
            return true;
        }
        // Si l'étudiant est null, throw une exception IllegalArgumentException
        else if (studentEntity == null){
            throw new IllegalArgumentException("Student is null");
        } else if (newBookIds == null || newBookIds.isEmpty()){
            throw new IllegalArgumentException("Book list is null or empty");
        } else {
            throw new IllegalArgumentException("Student is null and book list is null or empty");
        }
    }

}
