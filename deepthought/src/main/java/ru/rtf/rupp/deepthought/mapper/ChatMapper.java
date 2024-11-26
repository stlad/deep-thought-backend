package ru.rtf.rupp.deepthought.mapper;

import org.mapstruct.Mapper;
import ru.rtf.rupp.deepthought.dto.ChatDto;
import ru.rtf.rupp.deepthought.dto.ChatMemberInfoDto;
import ru.rtf.rupp.deepthought.entity.Chat;
import ru.rtf.rupp.deepthought.entity.ChatMemberInfo;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatDto toDTO(Chat entity);

    ChatMemberInfoDto toDto(ChatMemberInfo entity);

}