package com.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotBlank(message = "first name")
    private String firstName;

    @NotBlank(message = "last name")
    private String lastName;

    @Email(message = "invalid email")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number")
    private String mobile;
    
}
