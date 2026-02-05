package com.vriur.user_service.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDto (
    @NotBlank @Size(min = 8, max = 32) String username,
    @NotBlank @Size(min = 8, max = 32) @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,32}$", message = "Password must contain upper/lowercase letters, a number, and a special character") String password,
    @NotBlank @Size(min = 2, max = 32) String name,
    @JsonProperty("last_name") @NotBlank @Size(min = 2, max = 32) String lastName,
    @NotBlank @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")String phone,
    @NotBlank @Email String email,
    @JsonProperty("date_of_birth") @NotNull LocalDate dateOfBirth
) {}