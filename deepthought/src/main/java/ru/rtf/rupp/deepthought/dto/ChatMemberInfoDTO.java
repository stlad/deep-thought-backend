package ru.rtf.rupp.deepthought.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.rtf.rupp.deepthought.enums.ChatRole;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatMemberInfoDTO {

    private UUID id;

    @Schema(description = "DTO пользователя")
    private UserDTO user;

    @Schema(description = "DTO чата")
    private ChatDTO chat;

    private ChatRole chatRole;
}
