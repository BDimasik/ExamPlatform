package me.smorodin.exam.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class AuthRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}