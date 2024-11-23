package ru.rtf.rupp.deepthought.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "ДТО регистрации пользователя")
public class UserRegistrationDTO {

    @Schema(description = "Логин")
    @NotEmpty(message = "Логин не должен быть пустым полем")
    private String login;

    @Schema(description = "Почта")
    @NotEmpty(message = "Почта не должна быть пустым полем")
    private String email;

    @Schema(description = "Пароль")
    @NotEmpty(message = "Пароль не должен быть пустым полем")
    private String password;
}
