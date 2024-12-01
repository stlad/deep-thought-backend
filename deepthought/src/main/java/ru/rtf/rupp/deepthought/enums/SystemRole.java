package ru.rtf.rupp.deepthought.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
@Getter
public enum SystemRole {
    ROLE_USER(1),
    ROLE_ADMIN(2);

    private final Integer code;

    public SimpleGrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(this.toString());
    }
}
