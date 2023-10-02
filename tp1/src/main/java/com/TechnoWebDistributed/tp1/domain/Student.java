package com.TechnoWebDistributed.tp1.domain;

import com.TechnoWebDistributed.tp1.model.BookEntity;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

// domain is used in service layer
@Builder
public record Student (
        UUID id,

        String firstName,

        String lastName,

        String email,

        Integer age
) {
}
