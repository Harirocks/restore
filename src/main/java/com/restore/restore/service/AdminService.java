package com.restore.restore.service;

import com.restore.restore.dto.request.AdminRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public String save(AdminRequest adminRequest) {
        return "Admin saved successfully";
    }
}
