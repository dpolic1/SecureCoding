package com.aiis.project.security.service;

import com.aiis.project.model.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);
}
