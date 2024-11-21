package ru.rtf.rupp.deepthought.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_message")
public class Message {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String content;

    private LocalDateTime postedAt;

    @ManyToOne
    @JoinColumn(name = "link_author", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "link_chat", nullable = false)
    private Chat chat;

    @Builder
    public Message(String content, User user, Chat chat){
        this.content = content;
        this.postedAt = LocalDateTime.now();
        this.chat = chat;
        this.user = user;
    }
}
