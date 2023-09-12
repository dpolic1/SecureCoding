package com.aiis.project.service;

import com.aiis.project.dto.UserDTO;
import com.aiis.project.model.User;
import com.aiis.project.model.Role;
import com.aiis.project.repository.UserRepository;
import com.aiis.project.repository.UserRoleRepository;
import com.aiis.project.request.UserLoginRequest;
import com.aiis.project.request.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository){
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that ID does not exist.")
        );
        return new UserDTO(user);
    }

    @Override
    public UserDTO login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByUsernameAndPassword(userLoginRequest.getUsername(), userLoginRequest.getPassword())
                .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that username and password does not exist.")
        );
        return new UserDTO(user);
    }

    @Override
    public void create(UserRequest userRequest) {
        try {
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            //user.setUserRoles(userRoleRepository.getById(1L));
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that username already exists.");
        }
    }

    @Override
    public void register(UserRequest userRequest) {
        try {
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
            Role role = userRoleRepository.findByRole("ROLE_USER");
            user.getRoles().add(role);
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that username already exists.");
        }
    }

    @Override
    public void updateById(Long id, UserRequest userRequest) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setUsername(userRequest.getUsername());
            updatedUser.setEmail(userRequest.getEmail());
            updatedUser.setPassword(userRequest.getPassword());
            //updatedUser.setUserRole(userRoleRepository.getById(1L));
            userRepository.save(updatedUser);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that ID does not exist.");
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with that ID does not exist.");
        }
    }

    @Override
    public void registerInsecure(UserRequest userRequest) {
        try {
            User user = new User();
            user.setUsername(userRequest.getUsername());
            user.setEmail(userRequest.getEmail());

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(userRequest.getPassword().getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }

            user.setPassword(sb.toString());
            user.setPassword(userRequest.getPassword());
            Role role = userRoleRepository.findByRole("ROLE_USER");
            user.getRoles().add(role);
            userRepository.save(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that username already exists.");
        }
    }
}
