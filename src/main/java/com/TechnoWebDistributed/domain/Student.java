package com.TechnoWebDistributed.domain;

import lombok.Builder;

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
