package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtf.rupp.deepthought.entity.UserRole;
import ru.rtf.rupp.deepthought.enums.SystemRole;

import java.util.UUID;

public interface SystemRoleRepository extends JpaRepository<UserRole, UUID> {
    boolean existsByUser_LoginAndRole(String login, SystemRole role);

}
