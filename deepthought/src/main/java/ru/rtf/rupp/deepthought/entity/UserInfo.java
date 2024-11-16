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
@Table(name = "t_user_info")
public class UserInfo {
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "link_user", referencedColumnName = "id")
    private User user;
}
