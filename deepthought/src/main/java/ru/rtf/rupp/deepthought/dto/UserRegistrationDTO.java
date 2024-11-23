package ru.rtf.rupp.deepthought.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "ДТО регистрации пользователя")
public class UserRegistrationDTO {

    @Schema(description = "Логин")
    @NotBlank(message = "Логин не должен быть пустым полем")
    private String login;

    @Schema(description = "Почта")
    @NotBlank(message = "Почта не должна быть пустым полем")
    private String email;

    @Schema(description = "Пароль")
    @NotBlank(message = "Пароль не должен быть пустым полем")
    private String password;
}
