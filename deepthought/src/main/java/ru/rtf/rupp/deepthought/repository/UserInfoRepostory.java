package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtf.rupp.deepthought.entity.UserInfo;

import java.util.UUID;

public interface UserInfoRepostory extends JpaRepository<UserInfo, UUID> {
}
