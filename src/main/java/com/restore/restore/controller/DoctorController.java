package com.restore.restore.controller;

import com.restore.restore.dto.request.DoctorRequest;
import com.restore.restore.dto.response.ApiResponse;
import com.restore.restore.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @RequestMapping("/mock")
    public ResponseEntity<?> mockDoctor(){
        DoctorRequest doctorRequestDTO = new DoctorRequest();
        doctorRequestDTO.setDoctorName("Vikram");
        doctorRequestDTO.setDoctorSpeciality("Pulmonologist");
        doctorRequestDTO.setGender("Male");
        doctorRequestDTO.setAge(29);
        doctorRequestDTO.setEmail("viki@gmail.com");
        doctorRequestDTO.setPassword("vikram@123");
        doctorRequestDTO.setPhoneNo(9786453120l);
        doctorRequestDTO.setRole("Doctor");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(doctorRequestDTO);
    }


    @PostMapping("save-doctor")
    public ResponseEntity<?> saveUser(@RequestBody DoctorRequest doctorRequest){

        String id=doctorService.save(doctorRequest);

        ApiResponse apiResponse=new ApiResponse();

        apiResponse.setHttpStatus(HttpStatus.ACCEPTED);
        apiResponse.setMessage("Doctor details saved successfully");
        apiResponse.setData(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse);
    }
}