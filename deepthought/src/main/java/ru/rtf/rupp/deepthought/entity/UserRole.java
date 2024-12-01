package ru.rtf.rupp.deepthought.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import ru.rtf.rupp.deepthought.config.converters.SystemRoleAttributeConverter;
import ru.rtf.rupp.deepthought.enums.SystemRole;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "t_user_role")
public class UserRole {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Convert(converter = SystemRoleAttributeConverter.class)
    private SystemRole role = SystemRole.ROLE_USER;

    @ManyToOne
    @JoinColumn(name = "link_user", referencedColumnName = "id")
    private User user;
}
