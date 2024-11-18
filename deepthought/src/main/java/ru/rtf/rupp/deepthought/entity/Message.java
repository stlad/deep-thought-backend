package ru.rtf.rupp.deepthought.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "t_message")
public class Message {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String Content;

    private LocalDateTime posted_at;

    @ManyToOne
    @JoinColumn(name = "link_author", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "link_chat")
    private Chat chat;

    @Builder
    public Message(String Content){
        this.Content = Content;

    }

}
