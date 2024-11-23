package ru.rtf.rupp.deepthought.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public class UserDTO {

    private UUID id;

    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Почта")
    private String email;

    @Schema(description = "Профиль пользователя")
    private UserProfileDTO userProfile;
}
