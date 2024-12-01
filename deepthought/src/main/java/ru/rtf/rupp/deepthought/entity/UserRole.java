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
@Builder
@Entity
@Table(name = "t_user_role")
public class UserRole {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Convert(converter = SystemRoleAttributeConverter.class)
    @Builder.Default
    private SystemRole role = SystemRole.USER;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_user", referencedColumnName = "id")
    private User user;
}
