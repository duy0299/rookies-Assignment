package com.rookies.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String gender;
    private String email;
    private String avatar;
    private String password;
    private String passwordConfirmation;


}