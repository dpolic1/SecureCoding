package com.aiis.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
