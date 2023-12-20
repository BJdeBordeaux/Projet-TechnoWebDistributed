package com.TechnoWebDistributed.domain;
import com.TechnoWebDistributed.model.StudentEntity;
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
