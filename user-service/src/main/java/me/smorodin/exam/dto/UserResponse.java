package me.smorodin.exam.dto;

import lombok.Data;

@Data
public final class UserResponse {
    private Long id;
    private String username;
    private String email;
}