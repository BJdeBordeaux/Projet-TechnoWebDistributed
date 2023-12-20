package com.TechnoWebDistributed.dto;

import lombok.Builder;

import java.util.UUID;

// DTO is used in controller layer
@Builder
public record StudentEmailDto(
        UUID id,

        String firstName,

        String lastName,

        String email

) {
}
