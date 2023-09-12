package com.aiis.project.service;

import com.aiis.project.model.Role;
import com.aiis.project.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository){
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<Role> findAll() {
        return userRoleRepository.findAll();
    }

}
