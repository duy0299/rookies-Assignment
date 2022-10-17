package com.rookies.assignment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @NotNull
    @NotEmpty
    private  String email;

    @NotNull
    @NotEmpty
    private  String password;
}
