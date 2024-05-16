package com.blogapp.payloads;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//DTO is Data Transfer Object. It is used for transferring the data across application
@Getter
@Setter
@Builder(builderMethodName = "of")
public class UserDto {
    private int id;
    private String userName;
    private String email;
    private String password;
    private String about;
}
