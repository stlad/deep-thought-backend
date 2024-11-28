package ru.rtf.rupp.deepthought.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatCreationDTO {

    @Schema(description = "Название чата")
    private String chat;

    @Schema(description = "Создатель чата")
    private String creator;
    
}
