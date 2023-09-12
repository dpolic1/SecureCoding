package com.aiis.project.security.controller;


import com.aiis.project.request.UserRequest;
import com.aiis.project.security.dto.LoginDTO;
import com.aiis.project.security.request.LoginRequest;
import com.aiis.project.security.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public LoginDTO login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid credentials"));
    }
}
