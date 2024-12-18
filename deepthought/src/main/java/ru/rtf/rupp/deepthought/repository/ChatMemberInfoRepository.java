package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.rtf.rupp.deepthought.entity.ChatMemberInfo;
import java.util.List;
import java.util.UUID;

public interface ChatMemberInfoRepository extends JpaRepository<ChatMemberInfo, UUID>{
    @Query("select cmi from ChatMemberInfo cmi where cmi.user.email = ?1")
    List<ChatMemberInfo> findAllByUserEmail(String email);

    @Query("select cmi from ChatMemberInfo cmi where cmi.chat.title = ?1")
    List<ChatMemberInfo> findAllByChatTitle(String title);

    @Query("select cmi from ChatMemberInfo cmi where cmi.chat.id = ?1")
    List<ChatMemberInfo> findAllByChatId(UUID id);

    @Query("select cmi from ChatMemberInfo cmi where cmi.chat.id = ?1 AND cmi.user.email = ?2")
    List<ChatMemberInfo> findByChatIdAndUser(UUID id, String email);

}
