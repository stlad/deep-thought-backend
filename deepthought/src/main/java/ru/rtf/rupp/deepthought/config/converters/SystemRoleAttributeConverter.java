package ru.rtf.rupp.deepthought.config.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.rtf.rupp.deepthought.enums.ChatRole;
import ru.rtf.rupp.deepthought.enums.SystemRole;

import java.util.Arrays;

@Converter
public class SystemRoleAttributeConverter implements AttributeConverter<SystemRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SystemRole systemRole) {
        return systemRole.getCode();
    }

    @Override
    public SystemRole convertToEntityAttribute(Integer code) {
        return Arrays.stream(SystemRole.values()).filter(role -> role.getCode().equals(code))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Не допустимое значение перечисления " + code));
    }
}
