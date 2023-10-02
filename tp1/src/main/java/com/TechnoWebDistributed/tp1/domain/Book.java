package com.TechnoWebDistributed.tp1.domain;
import com.TechnoWebDistributed.tp1.model.StudentEntity;
import lombok.*;
import java.util.UUID;

@Builder
public record Book (
        UUID id,

        String code,
        String name,
        StudentEntity studentEntity
) {

}
