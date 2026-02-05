package com.vriur.user_service.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserResponseDto(
    UUID id, 
    String username, 
    String name, 
    @JsonProperty("last_name") String lastName, 
    String phone, 
    String email, 
    @JsonProperty("date_of_birth") LocalDate dateOfBirth
) {}
