package ru.rtf.rupp.deepthought.dto;

import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MessageDto {
    private UUID id;
    private String text;
    private UUID chat;
    private String email; // replace with user id
    private String postedAt;
}