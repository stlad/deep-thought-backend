package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtf.rupp.deepthought.entity.ChatMemberInfo;

import java.util.UUID;

public interface ChatMemberInfoRepository extends JpaRepository<ChatMemberInfo, UUID>{
}
