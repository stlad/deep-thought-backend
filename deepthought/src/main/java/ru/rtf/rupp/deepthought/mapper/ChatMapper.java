package ru.rtf.rupp.deepthought.mapper;

import org.mapstruct.Mapper;
import ru.rtf.rupp.deepthought.dto.ChatDTO;
import ru.rtf.rupp.deepthought.dto.ChatMemberInfoDTO;
import ru.rtf.rupp.deepthought.entity.Chat;
import ru.rtf.rupp.deepthought.entity.ChatMemberInfo;

@Mapper(componentModel = "spring")
public interface ChatMapper extends BaseMapper {

    ChatDTO toDTO(Chat entity);

    ChatMemberInfoDTO toDTO(ChatMemberInfo entity);

}