package com.aiis.project.service;

import com.aiis.project.dto.UserDTO;
import com.aiis.project.request.UserLoginRequest;
import com.aiis.project.request.UserRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();

    UserDTO findById(Long id);

    UserDTO login(UserLoginRequest userLoginRequest);

    void create(UserRequest userRequest);

    void register(UserRequest userRequest);

    void updateById(Long id, UserRequest userRequest);

    void deleteById(Long id);

    void registerInsecure(UserRequest userRequest);
}
