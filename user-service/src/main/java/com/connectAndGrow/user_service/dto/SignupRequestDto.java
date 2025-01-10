package com.connectAndGrow.user_service.dto;

import lombok.Data;

@Data
public class SignupRequestDto {

    private String name;
    private String email;
    private String password;
    private String bio;
}
