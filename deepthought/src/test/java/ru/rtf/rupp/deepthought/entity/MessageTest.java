package ru.rtf.rupp.deepthought.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.rtf.rupp.deepthought.BaseContextTest;

class MessageTest extends BaseContextTest {

    @Test
    @DisplayName("Инит всех полей")
    void messageEntityDefaultFileds_init() {
        User user = User.builder()
                .login("test")
                .email("test@test.com")
                .password("test")
                .build();

        Chat chat = Chat.builder()
            .title("Some title")
            .build();

        Message message = Message.builder()
            .Content("Some test text")
            .chat(chat)
            .user(user)
            .build();

        Assertions.assertNotNull(message.getContent());
        Assertions.assertNotNull(message.getUser());
        Assertions.assertNotNull(message.getPostedAt());
        Assertions.assertNotNull(message.getChat());
        
    }
}