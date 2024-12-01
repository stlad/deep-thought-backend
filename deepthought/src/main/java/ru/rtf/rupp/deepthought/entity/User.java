package ru.rtf.rupp.deepthought.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.rtf.rupp.deepthought.enums.SystemRole;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
@Table(name = "t_user")
public class User implements UserDetails {

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChatMemberInfo> memberInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> systemRoles;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "link_user_profile", referencedColumnName = "id")
    private UserProfile profile;

    @Builder
    public User(String login, String password, String email) {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (systemRoles.isEmpty()) {
            return List.of(SystemRole.ROLE_USER.toAuthority());
        }
        return systemRoles.stream().map(r -> r.getRole().toAuthority()).toList();
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isDeleted;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !isRestricted;
    }

    public void addRole(SystemRole role) {
        if (systemRoles == null) {
            systemRoles = new HashSet<>();
        }
        this.getSystemRoles().add(UserRole.builder().user(this).role(role).build());
    }
}