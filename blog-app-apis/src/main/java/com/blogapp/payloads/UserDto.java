package com.blogapp.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//DTO is Data Transfer Object. It is used for transferring the data across application
@Getter
@Setter
//@Builder(builderMethodName = "of")
@NoArgsConstructor
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username should be equal to or more than 4 characters")
    private String userName;

    @Email(message = "Email Address is not valid")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 10, message = "Password should be of 3 to 10 characters only")
    private String password;

    @NotEmpty
    private String about;
}
