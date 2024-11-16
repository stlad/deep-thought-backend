package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtf.rupp.deepthought.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
