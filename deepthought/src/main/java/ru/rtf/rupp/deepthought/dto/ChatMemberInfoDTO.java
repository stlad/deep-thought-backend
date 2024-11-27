package ru.rtf.rupp.deepthought.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatMemberInfoDTO {

    @Schema(description = "DTO пользователя")
    private UserDTO user;

    @Schema(description = "DTO чата")
    private ChatDTO chat;
}
