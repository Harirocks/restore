package com.restore.restore.controller;

import com.restore.restore.dto.request.AdminRequest;
import com.restore.restore.dto.response.ApiResponse;
import com.restore.restore.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/mock")
    public ResponseEntity<?> mockUser(){
        AdminRequest adminRequestDTO = new AdminRequest();
        adminRequestDTO.setAdminName("Vignesh.D.V");
        adminRequestDTO.setAge(30);
        adminRequestDTO.setGender("Male");
        adminRequestDTO.setEmail("vignesh@gmail.com");
        adminRequestDTO.setPassword("vignesh@123");
        adminRequestDTO.setRole("Admin");
        adminRequestDTO.setPhoneNo(9876543456l);


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(adminRequestDTO);
    }

    @PostMapping("save-admin")
    public ResponseEntity<?> saveAdmin(@RequestBody AdminRequest adminRequest){
        String id = adminService.save(adminRequest);
        ApiResponse apiResponse=new ApiResponse();

        apiResponse.setHttpStatus(HttpStatus.ACCEPTED);
        apiResponse.setMessage("Admin saved successfully");
        apiResponse.setData(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }
}
