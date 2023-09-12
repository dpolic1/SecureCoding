package com.aiis.project.security.service;

import com.aiis.project.security.dto.LoginDTO;
import com.aiis.project.security.request.LoginRequest;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginRequest loginRequest);
}
