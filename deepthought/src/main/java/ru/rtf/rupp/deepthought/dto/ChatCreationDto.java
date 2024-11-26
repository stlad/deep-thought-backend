package ru.rtf.rupp.deepthought.dto;
import lombok.*;
import ru.rtf.rupp.deepthought.dto.ChatDto;
import ru.rtf.rupp.deepthought.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatCreationDto {
    private String chat;
    private String creator;
    
}
