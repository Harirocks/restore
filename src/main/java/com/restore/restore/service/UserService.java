package com.restore.restore.service;

import com.restore.restore.domain.Role;
import com.restore.restore.dto.request.UserRequest;
import com.restore.restore.dto.response.UserResponse;
import com.restore.restore.entity.User;
import com.restore.restore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    // Update a user's role
    public void updateUserRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setRole(newRole);
        userRepository.save(user);
    }



}

