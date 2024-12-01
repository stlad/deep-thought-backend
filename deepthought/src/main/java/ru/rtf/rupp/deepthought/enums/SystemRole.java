package ru.rtf.rupp.deepthought.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SystemRole {
    ROLE_ADMIN(1),
    ROLE_USER(2);

    private final Integer code;
}
