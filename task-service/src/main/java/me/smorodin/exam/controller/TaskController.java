package me.smorodin.exam.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @GetMapping("/all")
    public ResponseEntity<String> allTasks(Authentication authentication) {
        return ResponseEntity.ok("All tasks for user " + authentication.getName());
    }
}
