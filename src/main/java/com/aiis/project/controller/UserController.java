package com.aiis.project.controller;

import com.aiis.project.dto.UserDTO;
import com.aiis.project.request.UserLoginRequest;
import com.aiis.project.request.UserRequest;
import com.aiis.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public List<UserDTO> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO findUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/login")
    public UserDTO loginUser(@RequestBody @Valid UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

    @PostMapping()
    public void createUser(@RequestBody @Valid UserRequest userRequest){
        userService.register(userRequest);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid UserRequest userRequest){
        userService.register(userRequest);
    }

    @PutMapping("/{id}")
    public void updateUserById(@PathVariable Long id, @RequestBody @Valid UserRequest userRequest){
        userService.updateById(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteById(id);
    }

    @PostMapping("/insecureRegister")
    public void registerUserInsecure(@RequestBody @Valid UserRequest userRequest){
        userService.registerInsecure(userRequest);
    }
}
