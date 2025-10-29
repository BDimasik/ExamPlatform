package me.smorodin.exam.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.smorodin.exam.dto.AuthRequest;
import me.smorodin.exam.dto.AuthResponse;
import me.smorodin.exam.entity.Role;
import me.smorodin.exam.entity.User;
import me.smorodin.exam.service.DetailsProviderService;
import me.smorodin.exam.service.UserService;
import me.smorodin.exam.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final DetailsProviderService detailsProviderService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody User user) {
        user.setRoles(List.of(Role.USER));
        User registeredUser = userService.registerUser(user);

        final UserDetails userDetails = detailsProviderService.loadUserByUsername(registeredUser.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(
                jwt,
                registeredUser.getUsername(),
                registeredUser.getEmail(),
                "User registered successfully"
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        final UserDetails userDetails = detailsProviderService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        final User user = userService.findByUsername(authRequest.getUsername());

        return ResponseEntity.ok(new AuthResponse(
                jwt,
                user.getUsername(),
                user.getEmail(),
                "Login successful"
        ));
    }
}