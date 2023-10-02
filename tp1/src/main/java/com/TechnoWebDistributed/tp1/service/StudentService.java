package com.TechnoWebDistributed.tp1.service;

import com.TechnoWebDistributed.tp1.model.StudentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {
    public List<StudentEntity> getAll();

    public Optional<StudentEntity> getById(Long id);

    public Optional<StudentEntity> delete(Long studentId);

}
