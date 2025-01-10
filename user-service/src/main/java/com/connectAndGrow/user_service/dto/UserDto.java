package com.connectAndGrow.user_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private String bio;
    private LocalDateTime createdAt;
}
