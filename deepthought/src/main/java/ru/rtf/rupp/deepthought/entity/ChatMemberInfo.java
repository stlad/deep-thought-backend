package ru.rtf.rupp.deepthought.entity;


import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import ru.rtf.rupp.deepthought.config.converters.ChatRoleAttributeConverter;
import ru.rtf.rupp.deepthought.enums.ChatRole;

import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Convert(converter = ChatRoleAttributeConverter.class)
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
