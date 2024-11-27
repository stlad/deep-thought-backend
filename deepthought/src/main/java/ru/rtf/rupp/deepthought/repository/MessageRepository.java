package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ru.rtf.rupp.deepthought.entity.Message;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    @Query("select m from Message m where m.chat.id = ?1 ORDER BY m.postedAt DESC")
    List<Message> findAllMessagesByChatId(UUID id); // add offset and limit to query messages
}
