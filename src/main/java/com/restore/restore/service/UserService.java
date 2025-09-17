package com.restore.restore.service;

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

    // Save a new user
    public String save(UserRequest userRequest) {
        User user = User.builder()
                .userName(userRequest.getUserName())
                .gender(userRequest.getGender())
                .age(userRequest.getAge())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phoneNo(userRequest.getPhoneNo())
                .role(userRequest.getRole())
                .build();

        userRepository.save(user);
        return "User saved Successfully";
    }

    // Update existing user
    public String update(int id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        existingUser.setUserName(userRequest.getUserName());
        existingUser.setGender(userRequest.getGender());
        existingUser.setAge(userRequest.getAge());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setPhoneNo(userRequest.getPhoneNo());
        existingUser.setRole(userRequest.getRole());

        userRepository.save(existingUser);
        return "User Updated Successfully";
    }

    // Delete user
    public void delete(int id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id " + id);
        }
        userRepository.deleteById(id);
    }

    // Get single user
    public UserResponse getUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));

        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .gender(user.getGender())
                .age(user.getAge())
                .email(user.getEmail())
                .phoneNo(user.getPhoneNo())
                .build();
    }

    // Get all users with pagination
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}

