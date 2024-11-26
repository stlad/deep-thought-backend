package ru.rtf.rupp.deepthought.dto;

import lombok.*;
import ru.rtf.rupp.deepthought.dto.ChatDto;
import ru.rtf.rupp.deepthought.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatMemberInfoDto {
    private UserDTO user;
    private ChatDto chat;
}
