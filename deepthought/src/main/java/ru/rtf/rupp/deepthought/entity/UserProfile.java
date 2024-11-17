package ru.rtf.rupp.deepthought.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_user_profile")
public class UserProfile {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String about;

    private String status;

    /**
     * Навание файла с аватаркой вользователя
     */
    private String avatar;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private User user;
}
