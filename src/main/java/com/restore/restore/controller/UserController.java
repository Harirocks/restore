package com.restore.restore.controller;

import com.restore.restore.domain.Role;
import com.restore.restore.entity.User;
import com.restore.restore.repository.UserRepository;
import com.restore.restore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Only Admin can update user roles
    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUserRole(
            @PathVariable Long id,
            @RequestParam Role newRole) {

        userService.updateUserRole(id, newRole);
        return ResponseEntity.ok("Role updated successfully!");
    }
}

