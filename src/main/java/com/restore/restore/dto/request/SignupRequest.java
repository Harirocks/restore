package com.restore.restore.dto.request;

import com.restore.restore.domain.Role;
import lombok.Data;

@Data
public class SignupRequest {
    private String userName;
    private String gender;
    private Integer age;
    private String email;
    private String password;
    private String phoneNo;
    private Role role;
}