package com.restore.restore.controller;

import com.restore.restore.dto.request.UserRequest;
import com.restore.restore.dto.response.ApiResponse;
import com.restore.restore.dto.response.UserResponse;
import com.restore.restore.entity.User;
import com.restore.restore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class AuthController {
    @Autowired(required = true)
    private UserService userService;

    @RequestMapping("/mock")
    public ResponseEntity<?> mockUser(){
        UserRequest userRequestDTO = new UserRequest();
        userRequestDTO.setUserName("Hariharan.K");
        userRequestDTO.setAge(29);
        userRequestDTO.setGender("Male");
        userRequestDTO.setEmail("hari@gmail.com");
        userRequestDTO.setPassword("hari@123");
        userRequestDTO.setRole("User");
        userRequestDTO.setPhoneNo(9876543210l);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userRequestDTO);
    }

    @PostMapping("save-user")
    public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest){

        String id=userService.save(userRequest);

        ApiResponse apiResponse=new ApiResponse();

        apiResponse.setHttpStatus(HttpStatus.ACCEPTED);
        apiResponse.setMessage("User details saved successfully");
        apiResponse.setData(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody UserRequest userRequest){
        String updatedId = userService.update(id, userRequest);
        ApiResponse response = new ApiResponse();
        response.setMessage("User details updated successfully");
        response.setHttpStatus(HttpStatus.OK);
        response.setData(updatedId);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")int id){
        userService.delete(id);
        return ResponseEntity.ok("User details deleted successfully");
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id")int id){
        UserResponse userResponseDTO = userService.getUser(id);
        return ResponseEntity.ok(userResponseDTO);
    }
    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<User> all = userService.getAllUsers(pageable);
        return ResponseEntity.ok(all);
    }


}