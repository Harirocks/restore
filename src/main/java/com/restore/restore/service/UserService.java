package com.restore.restore.service;

import com.restore.restore.dto.request.UserRequest;

public class UserService {
    public String save(UserRequest userRequest) {
        return "User saved Successfully";
    }

    public String update(int id, UserRequest userRequest) {
        return "User Updated Successfully";
    }
}
