package ru.rtf.rupp.deepthought.entity;


import java.time.LocalDateTime;
import java.util.UUID;

import ru.rtf.rupp.deepthought.enums.ChatRole;

import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "t_chat_member_info")
public class ChatMemberInfo {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "link_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "link_chat")
    private Chat chat;

    @Enumerated(EnumType.ORDINAL)
    private ChatRole chatRole;

    private LocalDateTime joinedAt;

    @Builder
    public ChatMemberInfo(User user, Chat chat){
        this.chat = chat;
        this.user = user;
        this.chatRole = ChatRole.MEMBER;
        this.joinedAt = LocalDateTime.now();
    }

    
}
