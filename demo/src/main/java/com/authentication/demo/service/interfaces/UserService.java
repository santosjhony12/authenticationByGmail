package com.authentication.demo.service.interfaces;

import java.util.List;

import com.authentication.demo.entity.User;
import com.authentication.demo.web.dto.UserRegistrationRequest;

public interface UserService {
    User save(UserRegistrationRequest user);
    User getByEmail(String email);
    List<User> getAll();
    void deleteByEmail(String email);
}
