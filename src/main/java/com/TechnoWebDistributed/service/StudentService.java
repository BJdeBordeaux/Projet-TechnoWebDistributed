package com.TechnoWebDistributed.service;

import com.TechnoWebDistributed.model.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {
    public List<StudentEntity> getAll();

    public Optional<StudentEntity> getById(UUID id);

    public Optional<StudentEntity> createStudent(StudentEntity studentEntity);

    public Optional<StudentEntity> delete(UUID studentId);

}
