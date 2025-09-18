package com.restore.restore.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {


    private Long userId;
    private String userName;
    private String gender;
    private int age;
    private String email;
    private String phoneNo;
}