package me.smorodin.exam.controller;

import lombok.RequiredArgsConstructor;
import me.smorodin.exam.dto.UserResponse;
import me.smorodin.exam.entity.User;
import me.smorodin.exam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUsername(username);
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}