package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtf.rupp.deepthought.entity.Chat;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID>{
    
}
