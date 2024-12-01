package ru.rtf.rupp.deepthought.mapper;

import org.mapstruct.Mapper;
import ru.rtf.rupp.deepthought.dto.UserDTO;
import ru.rtf.rupp.deepthought.dto.UserProfileDTO;
import ru.rtf.rupp.deepthought.entity.User;
import ru.rtf.rupp.deepthought.entity.UserProfile;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper {

    UserProfileDTO toDTO(UserProfile entity);

    UserDTO toDTO(User entity);

}
