package ru.rtf.rupp.deepthought.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private UUID id;

    @Schema(description = "Логин")
    private String login;

    @Schema(description = "Почта")
    private String email;

    @Schema(description = "Профиль пользователя")
    private UserProfileDTO profile;

    @Schema(description = "Время регистрации")
    private LocalDateTime registeredAt;

    @Schema(description = " Флаг блокировки")
    private Boolean isRestricted;

    @Schema(description = "Флаг удаленности")
    private Boolean isDeleted;
}
