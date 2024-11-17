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

    private String email;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "link_user_profile", referencedColumnName = "id")
    private UserProfile profile;

    @Builder
    public User(String login, String password, String email){
        Objects.requireNonNull(login, "Логин является обязательным полем");
        this.login = login;
        this.password = password;
        this.email = email;
        this.registeredAt = LocalDateTime.now();
        this.isDeleted = false;
        this.isRestricted = false;
        this.profile = UserProfile.builder()
                .build();
    }
}
