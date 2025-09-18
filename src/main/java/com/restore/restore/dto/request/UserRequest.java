package com.restore.restore.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String userName;
    private String gender;
    private int age;
    private String email;
    private String password;
    private String phoneNo;
    private String role;

}