package ru.rtf.rupp.deepthought.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum ChatRole {
    MEMBER("Участник чата",0),
    CHAT_ADMIN("Администратор чата", 1);
    
    private final String description;
    private final Integer code;
}