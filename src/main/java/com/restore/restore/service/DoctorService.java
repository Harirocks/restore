package com.restore.restore.service;

import com.restore.restore.dto.request.DoctorRequest;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    public String save(DoctorRequest doctorRequest) {
        return "Doctor Save Successfully";
    }
}
