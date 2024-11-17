package ru.rtf.rupp.deepthought.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.rtf.rupp.deepthought.BaseContextTest;

class UserTests extends BaseContextTest {

    @Test
    @DisplayName("Инициализация всех полей по-умалочанию")
    void userEntityDefaultFileds_init() {
        User user = User.builder()
                .login("test login")
                .email("test@example.com")
                .password("test passwd")
                .build();

        Assertions.assertNotNull(user.getLogin());
        Assertions.assertNotNull(user.getEmail());
        Assertions.assertNotNull(user.getPassword());
        Assertions.assertNotNull(user.getRegisteredAt());
        Assertions.assertNotNull(user.getProfile());
        Assertions.assertFalse(user.getIsDeleted());
        Assertions.assertFalse(user.getIsRestricted());
    }

    @Test
    @DisplayName("Исключение, если логин пустой")
    void userBuild_throwsException_whenLoginIsNull() {
        var userBuilder = User.builder().login(null);

        Assertions.assertThrows(NullPointerException.class, userBuilder::build);
    }
}