package ru.rtf.rupp.deepthought.mapper;

import org.mapstruct.Mapper;
import ru.rtf.rupp.deepthought.entity.UserRole;
import ru.rtf.rupp.deepthought.enums.SystemRole;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BaseMapper {

    default Set<SystemRole> map(Set<UserRole> roles){
        return roles.stream().map(UserRole::getRole).collect(Collectors.toSet());
    }
}
