package com.restore.restore.dto.request;

import lombok.Data;

@Data
public class AdminRequest {
    private String adminName;
    private String gender;
    private int age;
    private String email;
    private String password;
    private long phoneNo;
    private String role;
}
