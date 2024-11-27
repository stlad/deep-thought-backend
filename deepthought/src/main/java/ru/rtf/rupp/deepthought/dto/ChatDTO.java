package ru.rtf.rupp.deepthought.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChatDTO {

    private UUID id;

    @Schema(description = "Название чата")
    private String title;
}
