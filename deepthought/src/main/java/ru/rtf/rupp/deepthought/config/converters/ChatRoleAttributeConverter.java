package ru.rtf.rupp.deepthought.config.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.rtf.rupp.deepthought.enums.ChatRole;

import java.util.Arrays;

@Converter
public class ChatRoleAttributeConverter implements AttributeConverter<ChatRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ChatRole chatRole) {
        return chatRole.getCode();
    }

    @Override
    public ChatRole convertToEntityAttribute(Integer code) {
        return Arrays.stream(ChatRole.values()).filter(role -> role.getCode().equals(code))
                .findAny().orElseThrow(() -> new IllegalArgumentException("Не допустимое значение перечисления " + code));
    }
}
