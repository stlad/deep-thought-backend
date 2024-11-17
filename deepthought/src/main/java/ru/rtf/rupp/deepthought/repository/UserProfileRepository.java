package ru.rtf.rupp.deepthought.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rtf.rupp.deepthought.entity.UserProfile;

import java.util.UUID;

public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {
}
