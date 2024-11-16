package ru.rtf.rupp.deepthought.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String login;

    private String password;

    private LocalDateTime registeredAt;

    /**
     * Флаг блокировки
     */
    private Boolean isRestricted;

    /**
     * Флаг удаленности
     */
    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private UserInfo info;

    @Builder
    public User(String login, String password){
        Objects.requireNonNull(login, "Логин является обязательным полем");
        this.login = login;
        this.password = password;
        this.registeredAt = LocalDateTime.now();
        this.info = UserInfo.builder()
                .user(this)
                .build();
    }
}
