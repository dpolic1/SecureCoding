package com.aiis.project.security.service;

import com.aiis.project.model.User;
import com.aiis.project.repository.UserRepository;
import com.aiis.project.security.dto.LoginDTO;
import com.aiis.project.security.request.LoginRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthenticationServiceImpl(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<LoginDTO> login(LoginRequest req) {
        Optional<User> user = userRepository.findByUsername(req.getUsername());

        if (user.isEmpty() || !isMatchingPassword(req.getPassword(), user.get().getPassword())) {
            return Optional.empty();
        }

        return Optional.of(
                new LoginDTO(jwtService.createJwt(user.get()))
        );
    }

    private boolean isMatchingPassword(String rawPassword, String encryptedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encryptedPassword);
    }
}
